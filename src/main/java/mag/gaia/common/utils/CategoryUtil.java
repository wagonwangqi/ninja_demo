//package mag.gaia.common.utils;
//
//import com.google.inject.Inject;
//import mag.gaia.common.models.BigCategory;
//import mag.gaia.common.models.SmallCategory;
//import mag.gaia.common.services.GenericService;
//import mag.gaia.staff.dto.BigCategoryDto;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CategoryUtil {
//    @Inject
//    GenericService<BigCategory> bigCategoryGenericService;
//    @Inject
//    GenericService<SmallCategory>smallCategoryGenericService;
//    public List<BigCategoryDto> categoryList(){
//        List<BigCategory> bigCategoryGenericServiceAll = bigCategoryGenericService.findAll();
//        List<SmallCategory> smallCategoryGenericServiceAll = smallCategoryGenericService.findAll();
//        List<BigCategoryDto> categoryDtoList=new ArrayList<>();
//
//        BigCategoryDto bigCategoryDto = new BigCategoryDto();
//        int k;
//        for (int j=0;j<bigCategoryGenericServiceAll.size();j++){
//            for(int i=0;i<smallCategoryGenericServiceAll.size();i++){
//                if (bigCategoryGenericServiceAll.get(j).getId()==
//                        smallCategoryGenericServiceAll.get(i).getBigCategoryId()){
//                        bigCategoryDto.setBigCategoryName(bigCategoryGenericServiceAll.get(j).getBigCategoryName());
//                }
//
//            }
//        }
//
//
//
//
//
//
//    return  null;
//    }
//}
