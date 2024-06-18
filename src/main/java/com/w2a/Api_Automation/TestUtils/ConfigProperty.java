package com.w2a.Api_Automation.TestUtils;

import org.aeonbits.owner.Config;

@Config.Sources({
        "file:src/test/resources/propertyFile/config.Properties"
})
public interface ConfigProperty extends Config {

    @Key("baseURI")
    public String getBaseURI();

    @Key("basePath")
    public String getBasePath();

    @Key("secretKey")
    public String getSecretKey();
@Key("testReportPath")
    public String getTestFilePath();
    @Key("testReportName")
    public String getTestReportName();

}
