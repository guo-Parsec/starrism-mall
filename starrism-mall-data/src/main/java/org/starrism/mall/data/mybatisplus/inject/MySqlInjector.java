package org.starrism.mall.data.mybatisplus.inject;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.starrism.mall.data.mybatisplus.inject.methods.MySqlInsertAllBatch;

import java.util.List;

/**
 * <p>mysql注入</p>
 *
 * @author hedwing
 * @since 2022/8/25
 **/
public class MySqlInjector extends DefaultSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
        methodList.add(new MySqlInsertAllBatch());
        return methodList;
    }
}
