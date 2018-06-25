package org.thorn.code.common.validator;

import org.junit.Test;
import org.thorn.code.common.validator.domain.Child;
import org.thorn.code.common.validator.domain.Person;

import java.util.ArrayList;
import java.util.Vector;

/**
 * todo：补充注释
 *
 * @author chenyun.chris
 * @since 2018.05.10
 */
public class ValidatorTest {

    @Test
    public void validate1() throws Exception {
        Child p = new Child();

        p.setName("chen");
        p.setAge(133);
        p.setNickName("fg");

        Validator validator = new Validator();

        Vector<String> result = validator.validate(p);

        System.out.println(result.toString());
    }

    @Test
    public void validate2() throws Exception {
        Person mother = new Person();

        Child p = new Child();

        p.setName("chen");
        p.setAge(130);
        p.setNickName("fg");
        p.setMother(mother);

        Person[] pp = {mother};
        p.setP(pp);

        p.setFriends(new ArrayList<Person>());
        p.getFriends().add(mother);

        Validator validator = new Validator();

        Vector<String> result = validator.validate(p);

        for(String r : result) {
            System.out.println(r);
        }


    }

}