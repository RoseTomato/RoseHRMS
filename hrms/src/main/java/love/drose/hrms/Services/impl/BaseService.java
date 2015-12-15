package love.drose.hrms.services.impl;

import com.github.pagehelper.PageHelper;
import love.drose.hrms.services.IService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Service基类
 * Created by lovedrose on 2015/11/18.
 */
public abstract class BaseService<T> implements IService<T> {
    Logger logger = LogManager.getLogger(BaseService.class);

    private Class<T> clazz;

    @Autowired
    protected Mapper<T> mapper;

    public Mapper<T> getMapper() {
        return mapper;
    }

    /**
     * 构造函数，通过反射技术，获取clazz实例化对象的Class
     */
    public BaseService() {
        logger.debug("<==");

        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class<T>) pt.getActualTypeArguments()[0];

        logger.debug("==>");
    }

    @Override
    public T findByProperty(String propertyName, Object value) {
        logger.debug("<== [napropertyNameme:" + propertyName + ", value;" + value + "]");
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo(propertyName, value);
        List<T> list = selectByExample(example);

        logger.debug("<==");
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public T findById(Object id) {
        logger.debug("<== [id:" + id + "] ==>");
        return mapper.selectByPrimaryKey(id);

    }

    @Override
    public T findByName(String name) {
        logger.debug("<== [name:" + name + "]");
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();

        criteria.andLike("name",  "%" + name + "%");
        List<T> list = selectByExample(example);

        logger.debug("<==");
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public T findOne(T t) {
        logger.debug("<== [t:" + t + "] ==>");
        return mapper.selectOne(t);
    }

    @Override
    public List<T> findAll() {
        logger.debug("<== ==>");
        return mapper.selectAll();
    }

    @Override
    public int save(T model) {
        logger.debug("<== [model:" + model + "] ==>");
        return mapper.insert(model);
    }

    @Override
    public int deleteById(Object id) {
        logger.debug("<== [id:" + id + "] ==>");
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delete(T model) {
        logger.debug("<== [model:" + model + "] ==>");
        return mapper.delete(model);
    }

    @Override
    public int update(T model) {
        logger.debug("<== [model:" + model + "] ==>");
        return mapper.updateByPrimaryKey(model);
    }

    @Override
    public int updateNotNull(T model) {
        logger.debug("<== [model:" + model + "] ==>");
        return mapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public List<T> selectByExample(Object example) {
        logger.debug("<== [example:" + example + "] ==>");
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> getPageList(int pageNum, int pageSize) {
        logger.debug("<==[pageNum:" +pageNum + ", pageSize:" + pageSize + "]");
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();

        // 分页查询
        PageHelper.startPage(pageNum, pageSize);
        logger.debug("==>");
        return selectByExample(example);
    }


    @Override
    public void deleteByField(String name, Object value) {
        logger.debug("<==[name:" +name + ", value:" + value + "]");
        try {
            Example example = new Example(clazz);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo(name, value);
            mapper.deleteByExample(example);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("==>");
    }

    @Override
    public List<T> findAllWhereIsNull(String name) {
        logger.debug("<==[name:" +name + "]");
        List<T> result = null;
        try {
            Example example = new Example(clazz);
            Example.Criteria criteria = example.createCriteria();
            criteria.andIsNull(name);
            result = selectByExample(example);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("==>");
        return result;
    }

    @Override
    public List<T> findAllByName(String name) {
        logger.debug("<==[name:" +name + "]");
        List<T> result = null;
        try {
            Example example = new Example(clazz);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("name", name);

            result = selectByExample(example);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("==>");
        return result;
    }

    @Override
    public List<T> findAllByProperty(String propertyName, Object value) {
        logger.debug("<==[propertyName:" +propertyName + ", value:" + value + "]");
        List<T> result = null;
        try {
            Example example = new Example(clazz);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo(propertyName, value);

            result = selectByExample(example);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("==>");
        return result;
    }
}
