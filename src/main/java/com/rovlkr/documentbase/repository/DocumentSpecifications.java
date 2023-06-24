package com.rovlkr.documentbase.repository;

import java.util.Set;

import org.springframework.data.jpa.domain.Specification;

import com.rovlkr.documentbase.entity.Category_;
import com.rovlkr.documentbase.entity.Document;
import com.rovlkr.documentbase.entity.Document_;
import com.rovlkr.documentbase.entity.Tag;
import com.rovlkr.documentbase.entity.Tag_;

import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.SetJoin;
import jakarta.persistence.criteria.Subquery;

public class DocumentSpecifications {

    private static final Specification<Document> TRUE_SPEC = (root, query, cb) -> cb.isTrue(cb.literal(true));

    private DocumentSpecifications() {}

    public static Specification<Document> documentMatchesText(String text) {
        if (text == null) {
            return TRUE_SPEC;
        }

        final String pattern = "%" + text.toLowerCase() + "%";
        return (root, query, cb) -> cb.or( //
                cb.like(cb.lower(root.get(Document_.name)), pattern), //
                cb.like(cb.lower(root.get(Document_.description)), pattern));
    }

    /**
     * tagIds in Document.tags.id
     */
    public static Specification<Document> documentContainsTags(Set<Long> tagIds) {
        if (tagIds == null || tagIds.isEmpty()) {
            return TRUE_SPEC;
        }

        return (root, query, cb) -> {
            Subquery<Document> subquery = query.subquery(Document.class);
            Root<Document> subqueryRoot = subquery.from(Document.class);
            SetJoin<Document, Tag> joinTags = subqueryRoot.join(Document_.tags);

            subquery.select(subqueryRoot).where(joinTags.get(Tag_.id).in(tagIds))
                    .groupBy(subqueryRoot.get(Document_.id))
                    .having(cb.equal(cb.countDistinct(joinTags.get(Tag_.id)), tagIds.size()));

            return cb.in(root).value(subquery);
        };
    }

    public static Specification<Document> documentHasCategory(Long categoryId) {
        if (categoryId == null) {
            return TRUE_SPEC;
        }

        return (root, query, cb) -> cb.equal(root.join(Document_.category).get(Category_.id), categoryId);
    }

    public static Specification<Document> documentHasSensibility(Boolean sensible) {
        if (sensible == null) {
            return TRUE_SPEC;
        }

        return (root, query, cb) -> cb.equal(root.get(Document_.sensible), sensible);
    }
}
