package org.thorn.code.common.protocol;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * jsf接口请求对象.
 *
 * @author chenyun by 2020-07-14 15:17
 * @since 1.0
 */
@Data
@ToString(callSuper = true)
public class ApiRequest<T> extends BaseRequest<T> {

    /**
     * 请求系统
     */
    private String requestSystem;

    /**
     * 请求时间
     */
    private Date requestDate = new Date();

    /**
     * 请求号
     */
    private String requestNo;

}
