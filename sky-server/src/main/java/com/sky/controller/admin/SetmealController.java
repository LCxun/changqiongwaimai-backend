package com.sky.controller.admin;


import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Api("套餐相关接口")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    @PostMapping
    @ApiOperation("新增套餐接口")
    public Result save(@RequestBody SetmealDTO setmealDTO){

        log.info("新增套餐：{}", setmealDTO);

        setmealService.saveWithDish(setmealDTO);
        return Result.success();

    }

    @GetMapping("/page")
    @ApiOperation("套餐分页查询接口")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("分页查询：{}", setmealPageQueryDTO);
        PageResult pageResult = setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping
    @ApiOperation("套餐批量删除接口")
    public Result delete(@RequestParam List<Long> ids){


        log.info("批量删除：{}", ids);

        setmealService.deleteBatch(ids);

        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("套餐查询接口")
    public Result<SetmealVO> getById(@PathVariable Long id){
        log.info("套餐查询接口：{}", id);

        SetmealVO setmealVO = setmealService.getByIdWithDish(id);
        return Result.success(setmealVO);
    }

    @PutMapping
    @ApiOperation("套餐修改接口")
    public Result update(@RequestBody SetmealDTO setmealDTO){

        log.info("套餐修改接口：{}", setmealDTO);
        setmealService.update(setmealDTO);

        return Result.success();
    }

    @PostMapping("/status/{status}")
    @ApiOperation("套餐起售停售接口")
    public Result startOrStop(@PathVariable Integer status, Long id){
        log.info("套餐起售停售接口：{}，{}", status, id);

        setmealService.startOrStop(status, id);
        return Result.success();
    }

}
