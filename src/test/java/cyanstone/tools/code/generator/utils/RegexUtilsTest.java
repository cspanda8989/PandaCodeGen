package cyanstone.tools.code.generator.utils;

import org.junit.Assert;
import org.junit.Test;

public class RegexUtilsTest {

    @Test
    public void testGetMatchedFieldType() {
        String result = RegexUtils.getMatchedFieldType("商品类型,可选{VIRTUAL(虚拟物品),POINT(积分), REAL(实物)}");
        Assert.assertEquals("VIRTUAL(虚拟物品),POINT(积分), REAL(实物)", result);
    }
}