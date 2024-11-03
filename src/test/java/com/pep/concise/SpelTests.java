package com.pep.concise;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import javax.annotation.Resource;

/**
 * spel表达式测试
 *
 * @author gang.liu
 */
@Slf4j
@SpringBootTest
public class SpelTests {

    /**
     * spring上下文
     */
    @Resource
    public ApplicationContext applicationContext;

    /**
     * 输出方法
     *
     * @return 字符串
     */
    public static String print() {
        return "Java!!!";
    }

    /**
     * 基础的spel测试
     */
    @Test
    void spelBaseTest() {
        SpelExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression("'Hello '.concat(#param)");
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setVariable("param", "World!!!");
        String value = expression.getValue(evaluationContext, String.class);
        log.info("expression evaluation result:{}", value);
    }

    /**
     * 静态方法测试
     *
     * @throws NoSuchMethodException 可能抛出的异常
     */
    @Test
    void spelStaticTest() throws NoSuchMethodException {
        SpelExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression("'Hello '.concat(#printJava())");
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.registerFunction("printJava", SpelTests.class.getMethod("print"));
        String value = expression.getValue(evaluationContext, String.class);
        log.info("expression evaluation result:{}", value);
    }

    /**
     * spring bean测试
     */
    @Test
    void spelBeanTest() {
        SpelExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression("@tokenManager.revoke(#param)");
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setBeanResolver(new BeanFactoryResolver(applicationContext));
        evaluationContext.setVariable("param", "Passion!!!");
        expression.getValue(evaluationContext, Void.class);
    }


}
