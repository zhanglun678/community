package com.zl.dto;

import com.zl.model.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页
 */
@Data
public class PaginationDTO {
    private List<Question> questionsList;
    private boolean showHomePage; // 首页
    private boolean showPreviousPage ; // 上一页
    private boolean showEndPage=true; // 尾页
    private boolean showNextPage=true; //下一页
    private Integer page; // 当前页
    private Integer totalPage;//尾页码
    private List<Integer> pages=new ArrayList<>();
    public void getPage(Integer page,Integer size, Integer totalSize) {
        int totalPage  = 0 ;
        //计算出总页码
        if(totalSize%size != 0 ){
            totalPage = (totalSize/size) + 1;
        }else if (totalSize%size == 0){
            totalPage = totalSize/size;
        }
        this.totalPage = totalPage;
        this.page = page;
        //计算出需要显示的页码
        if(page<=4){
            for (int i = 1; i <= totalPage; i++) {
                if(pages.size()>=7){
                    break;
                }
                pages.add(i);
            }
        }else if(page>totalPage-4){
            for(int i = 6 ; i>=0 ;i--){
                if(totalPage-i>=1){
                    pages.add(totalPage-i);
                }

            }
        }else{
            for(int i = 3 ;i>=1; i--){
                pages.add(page-i);
            }
            pages.add(page);
            for(int i = 1 ; i<=3 ;i++){
                pages.add(page+i);
            }

        }
        //判断需要显示的按钮
        if(page>4){
            showPreviousPage=true;
        }
        if(page>5){
            showHomePage=true;
        }
        if(page==totalPage){
            showNextPage = false;
            showEndPage=false;
        }
        if(pages.contains(totalPage-1)){
            showNextPage = true;
            showEndPage=false;
        }
        if(pages.contains(totalPage)){
            showNextPage = false;
            showEndPage=false;
        }
    }
}
