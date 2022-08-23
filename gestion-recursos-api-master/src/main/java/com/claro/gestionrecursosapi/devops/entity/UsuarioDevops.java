package com.claro.gestionrecursosapi.devops.entity;

import java.util.List;

public class UsuarioDevops {
    
    private String subjectKind;
    private String metaType;
    private String directoryAlias;
    private String domain;
    private String principalName;
    private String mailAddress;
    private String origin;
    private String originId;
    private String displayName;
    private String url;
    private String descriptor;
    private List<String> _links;


    
    

    public String getSubjectKind() {
        return this.subjectKind;
    }

    public void setSubjectKind(String subjectKind) {
        this.subjectKind = subjectKind;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPrincipalName() {
        return this.principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getMailAddress() {
        return this.mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOriginId() {
        return this.originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescriptor() {
        return this.descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public String getmetaType(){
        return metaType;
    }
    public void setmetaType(String metaType){
        this.metaType = metaType;
    }

    public void setdirectoryAlias(String directoryAlias){
        this.directoryAlias = directoryAlias;
    }
    public String getdirectoryAlias(){
        return directoryAlias;
    }
   
    
}
