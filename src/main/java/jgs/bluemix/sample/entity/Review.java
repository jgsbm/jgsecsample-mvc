package jgs.bluemix.sample.entity;

import lombok.Data;
import lombok.ToString;

/**
 * Review情報を表現するEntityです.
 *
 * @author ryozo
 */
@Data
@ToString(callSuper = true)
public class Review extends BaseEntity {
    private Integer evaluation;
    private String comment;
    private String productCode;
    private Customer customer;
}
