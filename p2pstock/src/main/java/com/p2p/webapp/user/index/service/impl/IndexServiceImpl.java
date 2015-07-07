package com.p2p.webapp.user.index.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.p2p.webapp.common.util.AppUtil;
import com.p2p.webapp.invest.investmgr.entity.Invest;
import com.p2p.webapp.invest.investmgr.vo.InvestVo;
import com.p2p.webapp.sysmgr.issuemgr.entity.InvestProduct;
import com.p2p.webapp.sysmgr.issuemgr.vo.InvestProductVo;
import com.p2p.webapp.user.index.dao.IndexDao;
import com.p2p.webapp.user.index.entity.IndexCount;
import com.p2p.webapp.user.index.service.IndexService;
import com.p2p.webapp.user.index.vo.IndexVo;

public class IndexServiceImpl implements IndexService{
	
	private IndexDao indexDao;
	public IndexVo queryInexInfo(){
		IndexVo indexVo = new IndexVo();
		IndexCount indexCount = indexDao.queryUserAllProfit();
		String trancount = indexDao.queryUserAllTrans();
		if(indexCount != null){
			indexVo.setProduct_buycount_all(AppUtil.numfor4(indexCount.getProduct_buycount_all()));
			indexVo.setUser_invest_profit(AppUtil.numfor4(indexCount.getUser_invest_profit()));			
		}else{
			indexVo.setProduct_buycount_all("0.00");
			indexVo.setUser_invest_profit("0.00");
		}

		indexVo.setTrancount(trancount);
		return indexVo;
	}
	
	/*
	 * 查询最新产品
	 */
	public List<InvestProductVo> queryNewProduct(){
		List<InvestProductVo> rlist = new ArrayList<InvestProductVo>();
		List<InvestProduct> datalist = indexDao.queryNewProduct();
		for(InvestProduct info:datalist){
			InvestProductVo rvo = new InvestProductVo();
			rvo.setCreate_date(info.getCreate_date());
			rvo.setInvt_product_name(info.getInvt_product_name());
			rvo.setStart_date(info.getStart_date());
			rvo.setEnd_date(info.getEnd_date());
			rlist.add(rvo);
		}
		return rlist;
	}
	/*
	 * 查询最新投资
	 */
	public List<InvestVo> queryNewInvest(){
		List<InvestVo> rlist = new ArrayList<InvestVo>();
		List<Invest> datalist = indexDao.queryNewInvest();
		for(Invest info:datalist){
			InvestVo rvo = new InvestVo();
			rvo.setCreate_date(info.getCreate_date());
			rvo.setUser_id(info.getUser_id());
			rvo.setAcount_prtfeeall(AppUtil.numFormat(info.getAcount_prtfeeall(), 2));
			rvo.setUser_name(AppUtil.fmtStr(info.getUser_name(), "0", 2));
			rvo.setInvest_prdouct_name(info.getInvest_prdouct_name());
			rlist.add(rvo);
		}
		return rlist;
	}
	public IndexDao getIndexDao() {
		return indexDao;
	}
	public void setIndexDao(IndexDao indexDao) {
		this.indexDao = indexDao;
	}
}
