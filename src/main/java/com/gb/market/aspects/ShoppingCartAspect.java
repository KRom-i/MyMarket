package com.gb.market.aspects;

import com.gb.market.services.СartService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Aspect
@Component
public class ShoppingCartAspect {

    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private СartService сartService;


    @After ("execution(public void com.gb.market.services.СartService.*(..))")
    private void ShoppingCartActions(JoinPoint joinPoint){

        System.out.println ("ShoppingCart actions (method): " + joinPoint.getSignature ().getName ());
        for (Object obj : joinPoint.getArgs()) {

            if (obj instanceof Long){
                System.out.println ("Product id: " + obj);

            } else if (obj instanceof HttpSession) {
                sendShoppingCard((HttpSession) obj);
            } ;
        }
    }

    private void sendShoppingCard (HttpSession session) {
        template.convertAndSend(
                "/cart/" + session.getId (),
                сartService.getCart (session));
    }
}
