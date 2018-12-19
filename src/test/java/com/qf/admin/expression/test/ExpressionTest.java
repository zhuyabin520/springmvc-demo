package com.qf.admin.expression.test;

import com.qf.admin.pojo.po.TbUser;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class ExpressionTest {

    @Test
    public void test1(){
        //表达式解析器
        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression("'hello'+'world'.concat('!!!')");
        String s = (String) expression.getValue();
        System.out.println(s);
    }

    @Test
    public void test2(){
        //获取对象
        TbUser user = new TbUser(1, "ldd");
        //获取上下文
        EvaluationContext evaluationContext = new StandardEvaluationContext(user);
        //表达式解析器
        ExpressionParser expressionParser = new SpelExpressionParser();

        String s = (String)expressionParser.parseExpression("username").getValue(evaluationContext);
        System.out.println(s);
    }


    @Test
    public void test3(){
        ExpressionParser parser = new SpelExpressionParser();
        String s = parser.parseExpression("'spring'.substring(2,4)").getValue(String.class);
        System.out.println(s);
    }


}
