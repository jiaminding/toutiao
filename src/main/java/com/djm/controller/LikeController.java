package com.djm.controller;

import com.djm.model.EntityType;
import com.djm.model.HostHolder;
import com.djm.model.News;
import com.djm.service.LikeService;
import com.djm.service.NewsService;
import com.djm.util.ToutiaoUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author dingjiamin
 * @workcode wb-djm332505
 * @date 2017/11/18
 */
@Controller
public class LikeController {

    @Autowired
    LikeService likeService;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    NewsService newsService;

    @RequestMapping(value = "/like", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String like(@RequestParam("newsId") int newsId) {
        long likeCount = likeService.like(hostHolder.getUser().getId(), EntityType.ENTITY_NEWS, newsId);

        newsService.updateLikeCount(newsId, (int)likeCount);
        return ToutiaoUtil.getJSONString(0, String.valueOf(likeCount));
    }

    @RequestMapping(value = "/dislike", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String dislike(@RequestParam("newsId") int newsId) {
        long likeCount = likeService.dislike(hostHolder.getUser().getId(), EntityType.ENTITY_NEWS, newsId);

        newsService.updateLikeCount(newsId, (int)likeCount);
        return ToutiaoUtil.getJSONString(0, String.valueOf(likeCount));
    }
}
