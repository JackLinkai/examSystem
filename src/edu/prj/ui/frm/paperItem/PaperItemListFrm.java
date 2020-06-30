package edu.prj.ui.frm.paperItem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.liuvei.common.win.ATableModel;

import edu.prj.bean.PaperBean;
import edu.prj.bean.PaperItemBean;
import edu.prj.service.PaperItemService;
import edu.prj.service.PaperService;
import edu.prj.service.impl.PaperItemServiceImpl;
import edu.prj.service.impl.PaperServiceImpl;
import edu.prj.ui.frm.paper.PaperListFrm;

public class PaperItemListFrm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2848897334146627264L;
	// 为窗体添加Panel类型的容器对象container属性，容器对象才可以放置按钮，输入框等。
	// 在initUI（）里实例化后，还要设置容器的布局
	private JPanel container;
	PaperItemService paperitemService = new PaperItemServiceImpl();
	public Long pk = null;
	public PaperListFrm listFrm = null;

	/**
	 * 无参构造方法
	 */
	public PaperItemListFrm() {
		// 1.初始化用户界面
		initUI();
		// 2.绑定事件
		bindEvent();
		// 3.自定义加载
		customLoad();
	}

	private void initUI() {
		// 定义当前窗体的宽高
		int width = 800;
		int height = 600;
		// 设置当前窗体的宽高
		this.setSize(width, height);
		// 计算当前窗体居中时的x,y坐标
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		// 定义当前窗体的位置
		this.setLocation(x, y);
		// 设置标题
		this.setTitle("查看试卷");
		// 设置窗体不允许最大化，即不允许改变窗体大小
		this.setResizable(false);
		// 实例化容器对象
		container = new JPanel();
		// 把容器的布局设置为null，代表绝对布局
		container.setLayout(null);
		// 将容器对象添加到当前窗体
		this.add(container);

		// 【Swing表格步骤4】：initUI()中初始化表格的方法
		initTableUI();
	}

	/**
	 * 【Swing表格步骤1】： pnlTablePane表格面板，用来放真正的表格对象
	 */

	private JScrollPane pnlTablePane;

	/**
	 * 【Swing表格步骤2】：tblObj是表格对象
	 */
	private JTable tblObj;

	/**
	 * 【Swing表格步骤3】：初始化表格相关对象，要调用获取表格数据的方法showListDate().
	 * <p>
	 * * 【Swing表格步骤4】：initUI()中初始化表格的方法
	 */
	private void initTableUI() {
		// 初始化表格对象
		tblObj = new JTable();
		// 初始化表格面板，并放入表格对象
		pnlTablePane = new JScrollPane(tblObj);
		// 设置表格面板的位置
		pnlTablePane.setBounds(10, 60, 775, 500);
		// 整个窗体的容器中添加表格面板
		container.add(pnlTablePane);
	}

	/*
	 * 2.绑定事件
	 */
	public void bindEvent() {
		// 设置默认的关闭操作，点击右上角的关闭按钮时退出整个应用程序
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// 为当前窗体添加监听器：目的是重写windowsClosing事件的处理方法
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				window_closing(e);

			}

		});
	}

	protected void window_closing(WindowEvent e) {
		// TODO Auto-generated method stub
		// 判断是否有传过来listfrm再做相应的事情
		if (listFrm != null) {
			listFrm.setVisible(true);
		}

		this.dispose();
	}

	// 加载选中行的数据
	public void loadData() {
		if (pk == null) {
			JOptionPane.showMessageDialog(null, "主键值为空，加载失败！");
			return;
		}
		PaperService paperService = new PaperServiceImpl();
		PaperBean bean = paperService.load(pk);
		if (bean == null) {
			JOptionPane.showMessageDialog(null, "主键值对应的数据不存在，加载失败！");
			return;
		}
		this.setTitle(this.getTitle() + "--【主键=" + pk + "】");

		String searchName = bean.getPaperName();
		java.util.List<PaperItemBean> list = null;

		list = paperitemService.listByName(searchName);
		ATableModel<PaperItemBean> tableModel = null;
		tableModel = new ATableModel<PaperItemBean>(list, 9) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * 5.4.3 重写getTitle()--返回【界面表格】各列的表头的名称，传入列的下标，返回对应的下表列的名称
			 */
			@Override
			public String getTitle(int columnIndex) {
				// TODO Auto-generated method stub
				if (columnIndex == 0) {
					return "项目Id";
				} else if (columnIndex == 1) {
					return "试卷名称";
				} else if (columnIndex == 2) {
					return "题目";
				} else if (columnIndex == 3) {
					return "选项A";
				} else if (columnIndex == 4) {
					return "选项B";
				} else if (columnIndex == 5) {
					return "选项C";
				} else if (columnIndex == 6) {
					return "选项D";
				} else if (columnIndex == 7) {
					return "答案";
				} else if (columnIndex == 8) {
					return "该题分数";
				}
				return "无";
			}

			/**
			 * 5.4.4 重写getCellValue()--返回【界面表格】各列的值，传入实体和列号，从【实体类集合】获取对应的值并返回
			 */
			@Override
			public Object getPropValue(PaperItemBean bean, int columnIndex) {
				// TODO Auto-generated method stub
				if (columnIndex == 0) {
					return bean.getItemID();
				} else if (columnIndex == 1) {
					return bean.getPapername();
				} else if (columnIndex == 2) {
					return bean.getQuestion();
				} else if (columnIndex == 3) {
					return bean.getItemA();
				} else if (columnIndex == 4) {
					return bean.getItemB();
				} else if (columnIndex == 5) {
					return bean.getItemC();
				} else if (columnIndex == 6) {
					return bean.getItemD();
				} else if (columnIndex == 7) {
					return bean.getAnswer();
				} else if (columnIndex == 8) {
					return bean.getScore();
				}
				return null;

			}

		};
		// 5.5设置表格对象的数据类型
		// 相当于，将表格模型tableModel 封装的列表数据list，显示到表格对象tblObj
		tblObj.setModel(tableModel);
	}

	/*
	 * 3.自定义加载:在初始化UI和绑定事件之后执行
	 */
	public void customLoad() {

	}
}
