package com.cos.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.product.action.Action;
import com.cos.product.action.ProductDeleteProcAction;
import com.cos.product.action.ProductListAction;
import com.cos.product.action.ProductOrderByBasicAction;
import com.cos.product.action.ProductOrderByCountAction;
import com.cos.product.action.ProductOrderByPriceAction;

@WebServlet("/product")
public class ProductController extends HttpServlet{
	private final static String TAG = "ProductController : ";
	private static final long serialVersionUID = 1L;
	
	public ProductController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://localhost:8000/productList/product?cmd=list
		String cmd = request.getParameter("cmd");
		System.out.println(TAG + "router : " + cmd);
		Action action = router(cmd);
		action.execute(request, response);		
	}
	
	public Action router(String cmd) {
		if(cmd.equals("list")) {
			return new ProductListAction();
		} else if (cmd.equals("orderByBasic")) {
			return new ProductOrderByBasicAction();
		} else if (cmd.equals("orderByPrice")) {
			return new ProductOrderByPriceAction();
		} else if (cmd.equals("orderByCount")) {
			return new ProductOrderByCountAction();
		} else if (cmd.equals("deleteProc")) {
			return new ProductDeleteProcAction();
		}
		return null;
	}
}
