package org.zerock.fc.annotations;

import org.zerock.fc.dao.BoardDAO;
import org.zerock.fc.service.BoardService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)//클래스타입으로 하려고한다,메소드로하려면 타입을 메소드로 바꿔줘야함
@Retention(RetentionPolicy.RUNTIME)//실행한다..?? 
public @interface Controller {

    String value();

}