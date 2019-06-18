package org.renhj.blog.controller;


import org.renhj.blog.pojo.dto.Result;
import org.renhj.blog.pojo.entity.JobsEntity;
import org.renhj.blog.service.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/jobs")
public class JobsController {
    @Autowired
    private JobsService jobsService;

    @GetMapping
    public Result getAllWithPage(@RequestParam(required = false, defaultValue = "1") Integer current,
                                 @RequestParam(required = false, defaultValue = "5") Integer size,
                                 @RequestParam(required = false) JobsEntity jobs) {
        return Result.ok(jobsService.findPageByWhere(jobs, current, size));
    }

    @PostMapping
    public Result postOne(@RequestBody JobsEntity jobs) {
        return Result.ok(jobsService.save(jobs));
    }

    @DeleteMapping
    public Result deleteAll(@RequestParam Long[] ids){
        return Result.ok(jobsService.deleteByIds(JobsEntity.class, "jobsId", Arrays.asList(ids)));
    }

    @GetMapping("/{id}")
    public Result getOne(@PathVariable Long id) {
        return Result.ok(jobsService.findById(id));
    }

    @PutMapping("/{id}")
    public Result putOne(@PathVariable Long id, @RequestBody JobsEntity jobs) {
        jobs.setJobsId(id);
        return Result.ok(jobsService.update(jobs));
    }

    @PatchMapping("/{id}")
    public Result patchOne(@PathVariable Long id, @RequestBody JobsEntity jobs) {
        jobs.setJobsId(id);
        return Result.ok(jobsService.updateSelective(jobs));
    }

    @DeleteMapping("/{id}")
    public Result deleteOne(@PathVariable Long id){
        return Result.ok(jobsService.deleteById(id));
    }

}
