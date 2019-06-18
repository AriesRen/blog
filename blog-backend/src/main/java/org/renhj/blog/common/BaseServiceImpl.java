package org.renhj.blog.common;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.renhj.blog.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

@Slf4j
public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    @Autowired
    private Mapper<T> mapper;
    private ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();

    @Override
    public T findById(Object id) {
        try {
            log.info("Base Service: 根据ID: {} 查找 {}",id, type);
            return mapper.selectByPrimaryKey(id);
        }catch (Exception e) {
            log.info("Base Service: 根据ID: {}查找{}未找到",id, type);
            throw new ServiceException("未找到！");
        }
    }

    @Override
    public List<T> findAll() {
        return mapper.selectAll();
    }

    @Override
    public T findOne(T record) {
        return mapper.selectOne(record);
    }

    @Override
    public List<T> findAllByWhere(T record) {
        return mapper.select(record);
    }

    @Override
    public PageInfo<T> findPageByWhere(T record, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<T> list = this.findAllByWhere(record);
        return new PageInfo<>(list);
    }

    @Override
    public Integer save(T record) {
        record.setCreatedTime(new Date());
        record.setUpdatedTime(record.getCreatedTime());
        return mapper.insert(record);
    }

    @Override
    public Integer saveSelective(T record) {
        record.setCreatedTime(new Date());
        record.setUpdatedTime(record.getCreatedTime());
        return mapper.insertSelective(record);
    }

    @Override
    public Integer update(T record) {
        record.setUpdatedTime(new Date());
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public Integer updateSelective(T record) {
        record.setUpdatedTime(new Date());
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer delete(T record) {
        return mapper.delete(record);
    }

    @Override
    public Integer deleteById(Object id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteByIds(Class<T> tClass, String property, List<Object> values) {
        Example example = new Example(tClass);
        example.createCriteria().andIn(property, values);
        return mapper.deleteByExample(example);
    }

    @Override
    public Integer deleteByWhere(T record) {
        return mapper.delete(record);
    }
}
