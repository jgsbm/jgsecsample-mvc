package jgs.bluemix.sample.repository;

import jgs.bluemix.sample.entity.Review;
import jgs.bluemix.sample.persistence.cloud.ProductMapper;
import jgs.bluemix.sample.persistence.onpremises.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * レビュー関連の操作を提供するRepositoryです.
 *
 * @author ryozo
 */
@Repository
public class ReviewRepository {

    @Autowired
    ReviewMapper reviewMapper;

    @Autowired
    ProductMapper productMapper;

    /**
     * 引数に指定されたProductCodeを保持する商品のReview一覧を取得します.
     * 検索結果は評価の降順、レビュー日(レコードの{@code created_at})の降順でソートされて返却されます.
     * @param productCode 検索対象とする商品のProductCode
     * @return 当該商品のレビュー一覧
     */
    public List<Review> findReviewByProductCode(String productCode) {
        return reviewMapper.findReviewByProductCode(productCode);
    }
}
