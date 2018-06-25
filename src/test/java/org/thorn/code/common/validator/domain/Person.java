package org.thorn.code.common.validator.domain;

import org.thorn.code.common.validator.Validate;

/**
 * todo：补充注释
 *
 * @author chenyun.chris
 * @since 2018.05.08
 */
public class Person {

    @Validate(value = {"notNull"})
    private String name;

    @Validate(value = {"notNull","length(16,18)"})
    private String idNo;

    @Validate(value = {"max(100)"})
    private int age;

    @Validate(value = {"notNull"})
    private Gender gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
