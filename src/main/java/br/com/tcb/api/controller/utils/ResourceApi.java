package br.com.intelector.api.controller.utils;

public class ResourceApi {

    public static final String APP_CONTEXT_API = "${app.context.api}";

    public static final String SERVICE_MENU = "menu";
    public static final String SERVICE_OPERACAO = "oper";
    public static final String SERVICE_USUARIO = "usua";
    public static final String SERVICE_PERFIL = "perf";
    public static final String SERVICE_AUTH = "auth";

    public static final String RESOURCE_API_SERVICE_MENU = APP_CONTEXT_API + "/" + SERVICE_MENU;
    public static final String RESOURCE_API_SERVICE_OPERACAO = APP_CONTEXT_API + "/" + SERVICE_OPERACAO;
    public static final String RESOURCE_API_SERVICE_USUARIO = APP_CONTEXT_API + "/" + SERVICE_USUARIO;
    public static final String RESOURCE_API_SERVICE_PERFIL = APP_CONTEXT_API + "/" + SERVICE_PERFIL;
    public static final String RESOURCE_API_SERVICE_AUTH = APP_CONTEXT_API + "/" + SERVICE_AUTH;



}
