package com.lyl.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.lyl.pojo.BasePojo;

import java.util.HashMap;
import java.util.Map;

public class Generator {
    public static void main(String[] args) {
        //构建代码生成器
        AutoGenerator mpg=new AutoGenerator();
        //构建配置器
        GlobalConfig gc=new GlobalConfig();
        gc.setAuthor("lyl");
        //定义路径
        String path=System.getProperty("user.dir");
        System.out.println(path);
//        gc.setOutputDir(path+"/src/main/java");
        mpg.setGlobalConfig(gc);
        // 定义pojo路径
        String pojopath = path + "/pojo";
        System.out.println(pojopath);
//        String othenpath = path + "/adimin";
        String othenpath = path + "/admin";
        System.out.println(othenpath);
        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://192.168.126.151:3306/shop?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("991229");
        mpg.setDataSource(dsc);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.lyl");
        pc.setEntity("pojo");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");
        Map<String,String> pathInfo =new HashMap<>();
        pathInfo.put("xml_path",othenpath+"/src/main/resources/com/lyl/mapper");
        pathInfo.put("entity_path",pojopath+"/src/main/java/com/lyl/pojo");
        pathInfo.put("mapper_path",othenpath+"/src/main/java/com/lyl/mapper");
        pathInfo.put("service_path",othenpath+"/src/main/java/com/lyl/service");
        pathInfo.put("service_impl_path",othenpath+"/src/main/java/com/lyl/service/impl");
        pathInfo.put("controller_path",othenpath+"/src/main/java/com/lyl/controller");
        pc.setPathInfo(pathInfo);
        mpg.setPackageInfo(pc);
//        // 自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                // to do nothing
//            }
//        };
//
//        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
//        // 如果模板引擎是 velocity
//        // String templatePath = "/templates/mapper.xml.vm";
//        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
//        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return path + "/src/main/resources/com/lyl/mapper"
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //每一个项目都是一个jar包
        strategy.setSuperEntityClass(BasePojo.class);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段,id字段不用在生成了，因为继承父类了
        strategy.setSuperEntityColumns("id");
        strategy.setInclude("ums_role_user");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        //生成代码
        mpg.execute();
    }
}
