package com.cxc.common.util;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class RandomCodeGenerator {

    public static List<String> getAllCode() {
        List<String> result = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            result.add(int2Str(i));
        }
        return result;
    }

    public static List<String> getCode(int num) {
        Set<String> result = Sets.newHashSet();
        Random random = new Random();
        while (true) {
            int val = random.nextInt(100);
            result.add(int2Str(val));
            if (result.size() == num) {
                break;
            }
        }
        return Lists.newArrayList(result);
    }

    public static List<String> getSubtractCode(List<String> codes) {
        Collection subtract = CollectionUtils.subtract(getAllCode(), codes);
        return (List<String>) subtract;
    }

    private static String int2Str(int code) {
        if (code < 10) {
            return "0" + code;
        } else {
            return String.valueOf(code);
        }
    }

}
