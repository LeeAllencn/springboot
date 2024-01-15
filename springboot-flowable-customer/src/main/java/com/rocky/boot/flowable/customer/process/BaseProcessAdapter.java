package com.rocky.boot.flowable.customer.process;

import com.rocky.boot.common.util.JsonUtil;
import com.rocky.boot.flowable.api.dto.ProcessNotifyDTO;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author : rocky
 * @date : created in 2023/4/17
 */
public abstract class BaseProcessAdapter<T> implements BaseProcessHandler {

    private Class<T> clazzModel = null;

    {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type clazzModel = parameterizedType.getActualTypeArguments()[0];
            if (clazzModel instanceof Class) {
                this.clazzModel = (Class<T>) clazzModel;
            }
        }
    }

    protected T genModel() {
        try {
            return clazzModel.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 路程通知适配
     * @param adapterDTO 适配DTO
     * @throws Exception 异常
     */
    public abstract void processNotifyAdapter(ProcessNotifyAdapterDTO<T> adapterDTO) throws Exception;

    @Override
    public void processNotify(ProcessNotifyDTO dto) throws Exception {
        ProcessNotifyAdapterDTO<T> adapterDTO = new ProcessNotifyAdapterDTO<>();
        BeanUtils.copyProperties(dto, adapterDTO);
        T model = JsonUtil.json2Obj(JsonUtil.map2Json(dto.getVariables()), clazzModel);
        adapterDTO.setVariables(model);
        processNotifyAdapter(adapterDTO);
    }


}
