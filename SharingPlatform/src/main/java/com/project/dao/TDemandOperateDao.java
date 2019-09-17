package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.entity.TDemandOperateEntity;

@Mapper
public interface TDemandOperateDao extends BaseDao<TDemandOperateEntity> {
    List<TDemandOperateEntity> findALL();

    List<TDemandOperateEntity> findList(TDemandOperateEntity tDemandOperate);


    int deleteByDemand(Object demandId);



}
