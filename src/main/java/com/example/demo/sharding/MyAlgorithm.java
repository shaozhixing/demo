/*
package com.example.demo.sharding;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import org.springframework.context.annotation.ComponentScan;

import java.util.Collection;

*/
/**
 * @author: shaozhixing
 * @date: 2019/8/31 12:01
 * @copyright: gofun
 *//*

@ComponentScan
public class MyAlgorithm implements PreciseShardingAlgorithm<String> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        String value = preciseShardingValue.getValue();
        int length = value.length();
        int ascii = value.charAt(length - 1);
        String suffix = ascii % 3 + "";
        for(String targetTableName : collection) {
            if(targetTableName.endsWith(suffix)) {
                return targetTableName;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String value = "a1b053a3d5114e3f98e4a397de2fb029";
        int length = value.length();
        int ascii = value.charAt(length - 1);
        String suffix = ascii % 10 + "";
        System.out.println(suffix);
    }
}
*/
