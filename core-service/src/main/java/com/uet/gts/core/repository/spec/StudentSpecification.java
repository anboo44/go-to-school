package com.uet.gts.core.repository.spec;

import com.uet.gts.core.model.entity.Student;
import org.springframework.data.jpa.domain.Specification;

public final class StudentSpecification {

    public static Specification<Student> canHaveName(String name) {
        if (name == null)
            return (root, query, cb) -> cb.and();
        else
            return (root, query, cb) -> cb.equal(root.get("name"), name);
    }
}
