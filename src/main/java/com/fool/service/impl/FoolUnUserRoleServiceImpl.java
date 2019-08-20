package com.fool.service.impl;

import com.fool.entity.FoolUnUserRole;
import com.fool.mapper.FoolUnUserRoleMapper;
import com.fool.service.FoolUnUserRoleService;
import com.fool.sys_common.SystemCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FoolUnUserRoleServiceImpl implements FoolUnUserRoleService {

    private static Logger logger = LoggerFactory.getLogger(FoolUnUserRoleServiceImpl.class);

    @Autowired
    FoolUnUserRoleMapper unUserRoleMapper;

    /**
     * 根据用户，查询用户下的用户角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<FoolUnUserRole> SelectUnUserRoleByUserAll(String userId) {
        return unUserRoleMapper.SelectUnUserRoleByUserAll(userId);
    }

    /**
     * 根据用户ID，删除所有角色配置
     *
     * @param userId
     * @return
     */
    @Override
    public int DeleteRolesByUser(String userId) {
        return unUserRoleMapper.DeleteRolesByUser(userId);
    }

    /**
     * @param userId
     * @param arrayRoles
     * @return boolean
     * 业务描述
     * 接收页面传递参数用户ID和需要编辑的角色ID
     * 获取用户当前所有角色
     * 1.与页面数据进行比较，针对数据库，如果与当前数据相等不操作，如果与当前数据不符，新增数据
     * 2.与页面数据进行比较，针对页面，如果与当前数据相等不操作，如果与当前数据不符，删除数据
     */
    @Override
    public boolean EditRoleByUser(String userId, String[] arrayRoles) {
        // 当前数据库中，用户匹配所有角色
        List<FoolUnUserRole> oldUnUserRoleAll = unUserRoleMapper.SelectUnUserRoleByUserAll(userId);

        /* 批量删除
         * ***** ***** ***** ***** ***** ***** ***** *****
         * 执行方式:
         * 如果Web传递的角色数组为空 return true
         * 如果数据库数据为空 不执行批量删除;Data != null OR Data > 0 执行删除
         * ----- ----- ----- ----- ----- ----- ----- -----
         * 删除策略:
         * Web数据与Data数据进行对比,如果Web数据不存在，而Data数据存在，则执行删除
         * ----- ----- ----- ----- ----- ----- ----- -----
         * 对比方式:
         */

        // 执行删除的配置数据ID数组(集合)
        List<Long> delUnIds = new ArrayList<>();

        if (0 == arrayRoles.length)
            return true;

        if (0 < oldUnUserRoleAll.size()) {
            for (FoolUnUserRole unUserRole : oldUnUserRoleAll) {
                int equalCount = 0;
                for (String webRoleId : arrayRoles) {
                    // WebRole VS DataRole
                    if (webRoleId.equals(unUserRole.getUrRoleid()))
                        equalCount++;
                }
                if (0 == equalCount)
                    delUnIds.add(unUserRole.getUrId());
            }
            // 执行删除
            int delFolag = unUserRoleMapper.DeleteOldRoleConfig(delUnIds);
            if (0 < delFolag)
                logger.info("成功：需要执行删除的" + delUnIds.toString());
            else
                logger.info("失败：需要执行删除的" + delUnIds.toString());
        }

        // 查询批量删除后所有当前用户的角色
        List<FoolUnUserRole> nowUnUserRoleAll = unUserRoleMapper.SelectUnUserRoleByUserAll(userId);

        // 如果执行批量删除后，数据库中无数据，则执行直接插入
        if (0 == nowUnUserRoleAll.size()) {

            // 测试 Start
            System.out.println("数据库中无数据，执行新增插入");
            // 测试 End

            for (int i = 0; i < arrayRoles.length; i++) {
                FoolUnUserRole foolUnUserRole = new FoolUnUserRole();
                foolUnUserRole.setUrId(SystemCode.GetIDLong());//设置ID
                foolUnUserRole.setUrUserid(userId);
                foolUnUserRole.setUrRoleid(arrayRoles[i]);
                foolUnUserRole.setUrDescription("User:" + userId + " = Role:" + arrayRoles[i]);

                int flag = unUserRoleMapper.InsertFoolUnUserRole(foolUnUserRole);
                if (0 < flag)
                    logger.info("成功: " + "ID：" + foolUnUserRole.getUrId() + " 配置：" + foolUnUserRole.getUrDescription());
                else
                    logger.info("失败: " + "ID：" + foolUnUserRole.getUrId() + " 配置：" + foolUnUserRole.getUrDescription());
            }
        }
        // 如果执行删除后，数据库中有数据，执行匹配后插入
        else {

            // 测试 Start
            System.out.println("数据库中有数据，执行匹配");
            // 测试 End

            // 需要执行插入的角色ID
            List<String> needInsertRoleId = new ArrayList<>();

            // 匹配 页面与数据库不同
            for (String webRoleId : arrayRoles) {
                int equalCount = 0;
                for (FoolUnUserRole dataRole : nowUnUserRoleAll) {
                    // Web VS Data
                    if (webRoleId.equals(dataRole.getUrRoleid()))
                        equalCount++;
                }
                if (0 == equalCount)
                    needInsertRoleId.add(webRoleId);
            }

            // 测试
            System.out.println("需要执行插入的角色：" + needInsertRoleId.toString());

            for (String needAddReloId : needInsertRoleId) {
                FoolUnUserRole foolUnUserRole = new FoolUnUserRole();
                foolUnUserRole.setUrId(SystemCode.GetIDLong());//设置ID
                foolUnUserRole.setUrUserid(userId);
                foolUnUserRole.setUrRoleid(needAddReloId);
                foolUnUserRole.setUrDescription("User:" + userId + " = Role:" + needAddReloId);
                int flag = unUserRoleMapper.InsertFoolUnUserRole(foolUnUserRole);
                if (0 < flag)
                    logger.info("成功: " + "ID：" + foolUnUserRole.getUrId() + " 配置：" + foolUnUserRole.getUrDescription());
                else
                    logger.info("失败: " + "ID：" + foolUnUserRole.getUrId() + " 配置：" + foolUnUserRole.getUrDescription());
            }
        }
        return true;
    }

}
