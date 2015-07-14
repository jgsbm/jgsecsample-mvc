package jgs.bluemix.sample.service;

import jgs.bluemix.sample.entity.Review;
import jgs.bluemix.sample.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * レビュー関連のビジネスロジックを提供するサービスです.
 *
 * @author ryozo
 */
@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    /**
     * 引数に指定されたProductCodeを保持する商品のReview一覧を取得します.
     * 検索結果は評価の降順、レビュー日(レコードの{@code created_at})の降順でソートされて返却されます.
     * @param productCode 検索対象とする商品のProductCode
     * @return 当該商品のレビュー一覧
     */
    public List<Review> findReviewByProductCode(String productCode) {
        return reviewRepository.findReviewByProductCode(productCode);
    }

}
