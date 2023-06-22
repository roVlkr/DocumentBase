package com.rovlkr.documentbase.repository;

import java.util.Set;

import org.springframework.data.jpa.domain.Specification;

import com.rovlkr.documentbase.entity.CategoryEntity_;
import com.rovlkr.documentbase.entity.DocumentEntity;
import com.rovlkr.documentbase.entity.DocumentEntity_;
import com.rovlkr.documentbase.entity.TagEntity;
import com.rovlkr.documentbase.entity.TagEntity_;

import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.SetJoin;
import jakarta.persistence.criteria.Subquery;

public class DocumentSpecifications {

    private static final Specification<DocumentEntity> TRUE_SPEC = (root, query, cb) -> cb.isTrue(cb.literal(true));

    private DocumentSpecifications() {}

    public static Specification<DocumentEntity> documentMatchesText(String text) {
        if (text == null) {
            return TRUE_SPEC;
        }

        final String pattern = "%" + text.toLowerCase() + "%";
        return (root, query, cb) -> cb.or( //
                cb.like(cb.lower(root.get(DocumentEntity_.name)), pattern), //
                cb.like(cb.lower(root.get(DocumentEntity_.description)), pattern));
    }

    /**
     * tagIds in Document.tags.id
     */
    public static Specification<DocumentEntity> documentContainsTags(Set<Long> tagIds) {
        if (tagIds == null || tagIds.isEmpty()) {
            return TRUE_SPEC;
        }

        return (root, query, cb) -> {
            Subquery<DocumentEntity> subquery = query.subquery(DocumentEntity.class);
            Root<DocumentEntity> subqueryRoot = subquery.from(DocumentEntity.class);
            SetJoin<DocumentEntity, TagEntity> joinTags = subqueryRoot.join(DocumentEntity_.tags);

            subquery.select(subqueryRoot).where(joinTags.get(TagEntity_.id).in(tagIds))
                    .groupBy(subqueryRoot.get(DocumentEntity_.id))
                    .having(cb.equal(cb.countDistinct(joinTags.get(TagEntity_.id)), tagIds.size()));

            return cb.in(root).value(subquery);
        };
    }

    public static Specification<DocumentEntity> documentHasCategory(Long categoryId) {
        if (categoryId == null) {
            return TRUE_SPEC;
        }

        return (root, query, cb) -> cb.equal(root.join(DocumentEntity_.category).get(CategoryEntity_.id), categoryId);
    }

    public static Specification<DocumentEntity> documentHasSensibility(Boolean sensible) {
        if (sensible == null) {
            return TRUE_SPEC;
        }

        return (root, query, cb) -> cb.equal(root.get(DocumentEntity_.sensible), sensible);
    }
}
