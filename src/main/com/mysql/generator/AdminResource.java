/*
 *    Copyright (c) 2018-2025, songfayuan All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the 霖梓控股 developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: songfayuan (1414798079@qq.com)
 */

package com.mysql.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.api.common.utils.StringUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author songfayuan
 * @date 2017/10/29
 */
public class AdminResource {


    public static void main(String[] args) throws Exception {


        String path = ResourceUtils.getURL("classpath:").getPath();
        System.out.println(path);
        String dir = null;
        if(StringUtil.isNotBlank(path)){
            dir = path.split("target")[0];
        }
        String outputDir = dir + "src/test/tmp";

        System.out.println(outputDir);

        final String viewOutputDir = outputDir + "/view/";
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outputDir);
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        gc.setAuthor("quyixiao");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("56OyE#XW?25EcM=9OT");
        dsc.setUrl("jdbc:mysql://47.99.224.159:3306/ldd_biz?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();


        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        strategy.setSuperControllerClass("com.api.common.controller.ClientBaseController");

        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);


        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.api");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        // 生成的模版路径，不存在时需要先新建
        File viewDir = new File(viewOutputDir);
        if (!viewDir.exists()) {
            viewDir.mkdirs();
        }
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig("/templates/listvue.vue.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getGeneratorViewPath(viewOutputDir, tableInfo, ".vue");
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);


        //生成controller相关
        mpg.execute();
    }

    /**
     * 获取配置文件
     *
     * @return 配置Props
     */
    private static Properties getProperties() {
        // 读取配置文件
        Resource resource = new ClassPathResource("/config/application.properties");
        Properties props = new Properties();
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    /**
     * 页面生成的文件名
     */
    private static String getGeneratorViewPath(String viewOutputDir, TableInfo tableInfo, String suffixPath) {
        String name = StringUtils.firstToLowerCase(tableInfo.getEntityName());
        String path = viewOutputDir + "/" + name + "/index"  + suffixPath;
        File viewDir = new File(path).getParentFile();
        if (!viewDir.exists()) {
            viewDir.mkdirs();
        }
        return path;
    }
}
