package org.renhj.blog.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.renhj.blog.common.BaseEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "jobs")
@Entity
@ToString(callSuper = true)
public class JobsEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobsId;
    /**
     * cron 表达式
     */
    private String cronExpression;
    /**
     * bean 名称
     */
    private String beanName;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 方法参数
     */
    private String methodParams;
    /**
     * 备注
     */
    private String remark;
    /**
     * 任务状态 0 正常 1 暂停 2 正在执行 3 结束
     */
    private Byte jobsStatus;
}
