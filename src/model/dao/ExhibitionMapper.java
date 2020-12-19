package model.dao;

import java.util.List;

import model.Artwork;
import model.Exhibition;
import model.ExhibitionBuyTicket;

public interface ExhibitionMapper {
	
	public List<Exhibition> selectAllExhibitionForUser(int userNo);
	public List<Exhibition> selectAllExhibitionForNotUser();
	
	public List<Exhibition> selectExhibitionByTitleForUser(int userNo, String title);
	public List<Exhibition> selectExhibitionByTitleForNotUser(String title);
	
	public List<Artwork> selectArtworkInExhibition(int exhibitionNo);
	
	public int insertExhibition(Exhibition exh);
	
	public int increaseOneVisitorInExhibition(int exhibitionNo);
	
	public int updateExhibition(Exhibition exh);
	
	public int deleteExhibition(int exhibitionNo);
	
	public int insertExhBuyTicket(ExhibitionBuyTicket ebt);
	
	public List<ExhibitionBuyTicket> selectAllExhBuyTicketForUser(int userNo);
	
	public int countHavingTicket(int userNo, int exhibitionNo);
	
	public Exhibition selectExhibitionByNo(int exhibitionNo);
}
