package model.dao;

import java.util.List;

import model.OcApply;
import model.Onedayclass;

public interface OnedayclassMapper {

	public int insertOnedayclass(Onedayclass odc);   
 
	public int increaseOneApplicantInOnedayclass(int onedayclassNo);
	
	public int updateOnedayclass(Onedayclass odc);
	
	public int deleteOnedayclass(int onedayclassNo);

	public Onedayclass selectOnedayclassByNo(int onedayclassNo);

	/** (비로그인용) onedayclass 전체 목록 반환 */
	public List<Onedayclass> selectAllOnedayclass();
	
	/** (로그인용) onedayclass 전체 목록 반환 
	public List<Onedayclass> selectAllOnedayclassForUser(int userNo);*/
	
	public int insertOcApply(OcApply oca);
}
