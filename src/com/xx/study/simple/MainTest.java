package com.xx.study.simple;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: XX
 * @Description:
 * @Since: JDK1.8
 * @Version: V1.0
 * @CreateDate: Create in 10:50 2021/11/23
 * @ModifiedDate:
 * @Copyright: ©
 */
public class MainTest {

    public static void main(String[] args) {

        // 根据userId 查出 这个用户的 dept_id
        long deptId = 100L;
        // 查出部门表的所有层级结构 select dept_id,parent_id from sys_dept where status = 0 and del_flag = 0;
        List<DeptInfo> deptInfoList = new ArrayList<>();
        deptInfoList.add(new DeptInfo(100L, 0L));
        deptInfoList.add(new DeptInfo(101L, 0L));
        deptInfoList.add(new DeptInfo(102L, 0L));
        deptInfoList.add(new DeptInfo(103L, 0L));
        deptInfoList.add(new DeptInfo(100001L, 100L));
        deptInfoList.add(new DeptInfo(100002L, 100L));
        deptInfoList.add(new DeptInfo(100201L, 102L));
        deptInfoList.add(new DeptInfo(100202L, 102L));
        deptInfoList.add(new DeptInfo(100001001L, 100001L));
        deptInfoList.add(new DeptInfo(100001002L, 100001L));
        deptInfoList.add(new DeptInfo(100002001L, 100002L));
        deptInfoList.add(new DeptInfo(100002002L, 100002L));
        deptInfoList.add(new DeptInfo(100021001L, 100201L));
        deptInfoList.add(new DeptInfo(100021002L, 100201L));
        deptInfoList.add(new DeptInfo(100022001L, 100201L));
        deptInfoList.add(new DeptInfo(100022002L, 100201L));
        List<Long> queryDeptList = doRecursion(deptInfoList, deptId);
        System.out.println(queryDeptList);
        // 插叙所有需要查询的部门 select distinct user_id  from sys_user where status = 0 and del_flag = 0 and dept_id in (queryDeptList);

    }

    private static List<Long> doRecursion(List<DeptInfo> deptInfoList, long deptId) {
        List<Long> queryDeptList = new ArrayList<>();
        Queue<Long> queue = new LinkedList<Long>();
        queue.add(deptId);
        while (!queue.isEmpty()) {
            long parentId = queue.poll();
            List<Long> filterList = deptInfoList.stream()
                    .filter(deptInfo -> deptInfo.getParentId().equals(parentId))
                    .map(DeptInfo::getDeptId)
                    .collect(Collectors.toList());
            if (filterList.size() > 0) {
                queue.addAll(filterList);
                queryDeptList.addAll(filterList);
            }
        }
        return queryDeptList;
    }


    static class DeptInfo {
        Long deptId;
        Long parentId;

        public DeptInfo() {

        }

        public DeptInfo(Long deptId, Long parentId) {
            this.deptId = deptId;
            this.parentId = parentId;
        }

        public Long getDeptId() {
            return deptId;
        }

        public void setDeptId(Long deptId) {
            this.deptId = deptId;
        }

        public Long getParentId() {
            return parentId;
        }

        public void setParentId(Long parentId) {
            this.parentId = parentId;
        }
    }

}
