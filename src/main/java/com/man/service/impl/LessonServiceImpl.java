package com.man.service.impl;

import com.man.common.PageUtils;
import com.man.common.constant.AuthorizationConstants;
import com.man.common.form.LessonQuery;
import com.man.dao.LessonMapper;
import com.man.dao.UserMapper;
import com.man.entity.Lesson;
import com.man.entity.User;
import com.man.exception.SchoolException;
import com.man.service.LessonService;
import com.man.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class LessonServiceImpl implements LessonService {
	@Autowired
	private LessonMapper lessonMapper;

	@Autowired
	private UserMapper userMapper;

	@Override
	public PageUtils selectPage(LessonQuery param) {
		if (param == null || param.getPage() == null || param.getPageSize() == null){
			throw new SchoolException("查询参数不能为空");
		}
		//查询数据总条数
		int count = lessonMapper.selectCount(param);
		//查询到当前页
		int page = 0;
		int totalPage = 1;
		int pageSize = 0;
		//总页数
		if (param.getPage() != null && param.getPageSize() != null){
			page = param.getPage();
			pageSize = param.getPageSize();
			totalPage =(int)Math.ceil((double)count / param.getPageSize());
			param.setPage((page-1)*param.getPageSize());
		}
		List<Lesson> lessons = null;
		if (count > 0 ){
			lessons = lessonMapper.selectPage(param);
			if (lessons != null && !lessons.isEmpty()){
				List<User> users = userMapper.selectAll();
				Map<Integer, List<User>> group = users.stream().collect(Collectors.groupingBy(User::getId));
				lessons.forEach(lesson -> {
					User user = group.get(lesson.getLessonTeacher()) == null ? null:group.get(lesson.getLessonTeacher()).get(0);
					if (user != null){
						lesson.setTeacherName(user.getUsername());
					}

				});
			}
		}
		return new PageUtils(lessons,count,pageSize,page);
	}

	@Override
	public List<Lesson> selectAll() {
		return lessonMapper.selectAll();
	}

	@Override
	public List<Lesson> selectMyLesson() {
		LessonQuery param = new LessonQuery();
		param.setLessonTeacher(UserUtils.getUser().getId());
		return lessonMapper.selectPage(param);
	}

	@Override
	public void saveLesson(Lesson lesson) {
		lesson.setLessonTeacher(UserUtils.getUser().getId());
		lessonMapper.insertSelective(lesson);
	}

	@Override
	public void updateLesson(Lesson lesson) {
		lesson.setEditdate(new Date());
		lessonMapper.updateByPrimaryKeySelective(lesson);
	}

	@Override
	public void deleteLesson(Integer lessonId) {
		Lesson lesson = lessonMapper.selectByPrimaryKey(lessonId);
		if (lesson != null && lesson.getLessonTeacher().equals(UserUtils.getUser().getId())){
			lessonMapper.deleteByPrimaryKey(lessonId);
		}else{
			throw new SchoolException("只允许删除自己的课程");
		}
	}
}
