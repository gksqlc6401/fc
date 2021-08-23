package org.zerock.fc.annotations;

import org.zerock.fc.dao.BoardDAO;
import org.zerock.fc.service.BoardService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {

    String value();

}