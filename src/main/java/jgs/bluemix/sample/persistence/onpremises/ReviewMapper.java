package jgs.bluemix.sample.persistence.onpremises;

import jgs.bluemix.sample.entity.Review;

import java.util.List;

/**
 * Reviewsテーブルに関する操作を提供するMapperクラスです.
 *
 * @author ryozo
 */
public interface ReviewMapper {

    /**
     * 引数に指定されたProductIdを保持する商品のReview一覧を取得します.
     * 検索結果は評価の降順、レビュー日(レコードの{@code created_at})の降順でソートされて返却されます.
     *
     * @param productCode 検索対象とする商品のProductCode
     * @return 当該商品のレビュー一覧
     */
    List<Review> findReviewByProductCode(String productCode);

}