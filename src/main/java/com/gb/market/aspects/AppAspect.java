package com.gb.market.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AppAspect {

    private final String nameRoleAdmin = "ROLE_ADMIN";

    @Around ("execution(public boolean com.gb.market.services.ProductService.save(..))")
    public boolean auth(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if (!checkAuth()){
            return false;

        } else {
            System.out.println ("Add new product: " + proceedingJoinPoint.getArgs ()[0].toString ());
            return (Boolean) proceedingJoinPoint.proceed ();
        }
    }

    public boolean checkAuth(){
        for (GrantedAuthority role: SecurityContextHolder.getContext().getAuthentication ().getAuthorities ()) {
            if (role.getAuthority ().equals (nameRoleAdmin)){
                return true;
            }
        }
        return false;
    }
}