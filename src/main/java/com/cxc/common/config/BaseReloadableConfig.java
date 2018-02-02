package com.cxc.common.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;

abstract class BaseReloadableConfig {

    protected static final Logger logger = LoggerFactory
        .getLogger(BaseReloadableConfig.class);

    protected static final long DEFAULT_RELOAD_PERIOD = 60000L;

    private static final ScheduledExecutorService scheduler = Executors
        .newSingleThreadScheduledExecutor();

    private ScheduledFuture<?> task;

    private String filename;

    private File file;

    private long lastModify = 0L;

    private boolean warnedAlready = false;

    private boolean interrupted = false;

    private String className;

    @PostConstruct
    private void init() {

        logger.info("{} init file config task", getClassName());

        this.filename = getFilename();
        this.file = new File(filename);
        warnedAlready = false;
        interrupted = false;

        reload();

        if (!needReload()) {
            return;
        }

        Runnable command = new Runnable() {

            @Override
            public void run() {
                reload();
            }

        };

        if (task != null) {
            cancelTask();
        }
        task = scheduler.scheduleAtFixedRate(command, getInitialDelay(),
            getPeriod(), getTimeUnit());

    }

    //    @PreDestroy
    private void destroy() {

        logger.info("{} destroy file config task", getClassName());

        cancelTask();

        if (!scheduler.isShutdown()) {
            scheduler.shutdown();
            logger.info("{} shut down scheduler", getClassName());
        }
    }

    protected void cancelTask() {
        if (task == null) {
            return;
        }
        task.cancel(false);
    }

    protected abstract String getFilename();

    protected TimeUnit getTimeUnit() {
        return TimeUnit.MILLISECONDS;
    }

    protected long getInitialDelay() {
        return 0;
    }

    protected long getPeriod() {
        return DEFAULT_RELOAD_PERIOD;
    }

    protected boolean needReload() {
        return true;
    }

    protected String getClassName() {
        if (className == null) {
            className = getClass().getSimpleName();
            int idx = className.indexOf('$');
            if (idx != -1) {
                className = className.substring(0, idx);
            }
        }
        return className;
    }

    protected void reload() {
        if (this.interrupted) {
            cancelTask();
            return;
        }
        logger.info("{} start reload config file={}, interrupt={}",
            getClassName(), this.filename, this.interrupted);
        try {
            checkAndConfigure();
        } catch (Exception e) {
            logger.error("{} checkAndConfigure error:{}", getClassName(),
                this.filename, e);
        }

    }

    private void checkAndConfigure() {
        boolean fileExists;
        try {
            fileExists = this.file.exists();
        } catch (SecurityException e) {
            logger.warn(
                "{} Was not allowed to read check file existance, file:[{}].",
                getClassName(), this.filename);

            this.interrupted = true;
            return;
        }
        if (!fileExists) {
            this.filename = FilenameUtils.getName(this.filename);
            try {
                URL u = Thread.currentThread().getContextClassLoader()
                    .getResource(this.filename);
                if (u != null) {
                    this.file = new FileSystemResource(new File(u.toURI()))
                        .getFile();
                    fileExists = this.file.exists();
                }
            } catch (Exception e) {
                logger.error("{} check file exist fail through classloader:{}",
                    getClassName(), this.filename, e);
            }
        }

        if (!fileExists) {
            try {
                InputStream input = Thread.currentThread()
                    .getContextClassLoader().getResourceAsStream(this.filename);
                try {
                    loadSuccess(input);
                } finally {
                    input.close();
                }
                logger.info(
                    "{} load config file success through classloader:{}",
                    getClassName(), this.filename);
            } catch (Exception e) {
                logger.error("{} load config file fail through classloader:{}",
                    getClassName(), this.filename, e);
            }

        }

        if (fileExists) {
            try {
                if (logger.isDebugEnabled()) {
                    logger.debug("{} fileName:{} fileExist:{}", getClassName(),
                        filename, this.file.exists());
                }
                long l = this.file.lastModified();
                if (l > this.lastModify) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("{} file change, lastModify={}, l={}",
                            getClassName(), lastModify, l);
                    }
                    this.lastModify = l;
                    InputStream input = new FileInputStream(this.file);
                    try {
                        loadSuccess(input);
                    } finally {
                        input.close();
                    }
                    this.warnedAlready = false;
                } else {
                    if (logger.isDebugEnabled()) {
                        logger.debug(
                            "{} file not change, lastModify={}, current={}",
                            getClassName(), lastModify, l);
                    }
                }
            } catch (Exception e) {
                logger.error("{} [{}] occurrs error=", getClassName(),
                    this.filename, e);
            }
        } else if (!this.warnedAlready) {
            logger.debug("{} [{}] does not exist.", getClassName(),
                this.filename);
            this.warnedAlready = true;
        }
    }

    protected abstract void loadSuccess(InputStream input);
}
