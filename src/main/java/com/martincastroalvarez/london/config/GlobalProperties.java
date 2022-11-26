package com.martincastroalvarez.london;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Validated
@Component
@ConfigurationProperties("")
class GlobalProperties {
    // --------------------------------------------------------------------
    // Spring Boot has many useful features including externalized
    // configuration and easy access to properties defined in properties
    // files
    //
    // The official documentation advises that we isolate configuration
    // properties into separate POJOs.
    //
    // Spring will automatically bind any property defined in our
    // property file that has the prefix mail and the same name
    // as one of the fields in the ConfigProperties class.
    //
    // References:
    // - https://www.baeldung.com/configuration-properties-in-spring-boot
    // --------------------------------------------------------------------

    @NotEmpty
    private String environment;

    @NotEmpty
    private String salt;

    private Boolean debug;

    public String getEnvironment() { return environment; }
    public void setEnvironment(String environment) { this.environment = environment; }

    public String getSalt() { return salt; }
    public void setSalt(String salt) { this.salt = salt; }

    public Boolean getDebug() { return debug; }
    public void setDebug(Boolean debug) { this.debug = debug; }

}
