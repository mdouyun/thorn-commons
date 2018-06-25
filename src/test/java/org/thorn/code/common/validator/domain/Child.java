package org.thorn.code.common.validator.domain;

import org.thorn.code.common.validator.Validate;

import java.util.List;

/**
 * todo：补充注释
 *
 * @author chenyun.chris
 * @since 2018.05.09
 */
public class Child extends Person {

    private String nickName;

    @Validate("notEmpty")
    private String[] toys;

    @Validate(value = "notNull")
    private Person father;

    @Validate(value = "notNull")
    private Person mother;

    @Validate("notEmpty")
    private List<Person> friends;

    @Validate("notEmpty")
    private Person[] p;

    public Person[] getP() {
        return p;
    }

    public void setP(Person[] p) {
        this.p = p;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String[] getToys() {
        return toys;
    }

    public void setToys(String[] toys) {
        this.toys = toys;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public List<Person> getFriends() {
        return friends;
    }

    public void setFriends(List<Person> friends) {
        this.friends = friends;
    }
}
