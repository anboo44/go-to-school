package com.uet.gts.core.service.impl;

import com.uet.gts.core.model.entity.Student;
import com.uet.gts.core.repository.StudentRepository;
import com.uet.gts.core.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Student> getById(Integer sid) {
        return studentRepository.findById(sid);
    }

    @Override
    public Boolean isExistedById(Integer sid) {
        return studentRepository.countById(sid) > 0;
    }

    @Override
    public void deleteById(Integer sid) {
        studentRepository.deleteById(sid);
    }

    @Override
    public Integer countWithName(String name) {
        return name == null ? studentRepository.countAll(): studentRepository.countByName(name);
    }

    @Override
    public List<Student> getByMultiParams(String name, String orderBy, Integer limit, Integer offset) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery  = builder.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);
        criteriaQuery.select(root);

        if (name != null) {
            var equalNamePredicate = builder.equal(root.get("name"), name);
            criteriaQuery.where(equalNamePredicate);
        }
        if (orderBy != null) {
            var ascPattern = Pattern.compile("(\\w+)ASC$", Pattern.CASE_INSENSITIVE);
            var ascMatcher = ascPattern.matcher(orderBy);

            var descPattern = Pattern.compile("(\\w+)DES$", Pattern.CASE_INSENSITIVE);
            var descMatcher = descPattern.matcher(orderBy);

            var fieldNames = Arrays.stream(
                    Student.class.getDeclaredFields()
            ).map(Field::getName).collect(Collectors.toList());

            if (ascMatcher.find()) {
                var fieldName = ascMatcher.group(1);
                if (fieldNames.contains(fieldName)) {
                    criteriaQuery.orderBy(builder.asc(root.get(fieldName)));
                }
            }
            if (descMatcher.find()) {
                var fieldName = descMatcher.group(1);
                if (fieldNames.contains(fieldName)) {
                    criteriaQuery.orderBy(builder.desc(root.get(fieldName)));
                }
            }
        }

        var typedQuery = em.createQuery(criteriaQuery);
        if (limit != null) typedQuery.setMaxResults(limit);
        if (offset != null) typedQuery.setFirstResult((offset - 1) * limit);

        return typedQuery.getResultList();
    }
}
