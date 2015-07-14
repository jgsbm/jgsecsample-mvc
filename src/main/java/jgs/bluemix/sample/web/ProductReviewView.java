package jgs.bluemix.sample.web;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品のレビュー情報を表示するためのDTOです.
 *
 * @author ryozo
 */
@Data
public class ProductReviewView implements Serializable {
    private static final long serialVersionUID = 911484133905974935L;
    private String name;
    private Integer evaluation;
    private String comment;
}
