package br.com.tcb.api.security.utils;

public class HasAuthorityUtil {

    public static final String CONSULTAR = "hasAuthority(this.genAuthGrant('CONS'))";
    public static final String CADASTRAR = "hasAuthority(this.genAuthGrant('CADA'))";
    public static final String EXCLUIR = "hasAuthority(this.genAuthGrant('EXCL'))";

}
