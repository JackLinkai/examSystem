package edu.prj.ui.frm.score;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.liuvei.common.win.ATableModel;

import edu.prj.bean.*;
import edu.prj.service.*;
import edu.prj.service.impl.*;

public class StuScoreListFrm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8625644808229017311L;
	// 为窗体添加Panel类型的容器对象container属性，容器对象才可以放置按钮，输入框等。
	// 在initUI（）里实例化后，还要设置容器的布局
	private JPanel container;

	private JLabel lblTitle;// 管理界面的标题

	ExamService examService = new ExamServiceImpl();
	public String loginName = null;

	/**
	 * 无参构造方法
	 */
	public StuScoreListFrm() {
		// 1.初始化用户界面
		initUI();
		// 2.绑定事件
		bindEvent();
		// 3.自定义加载
		customLoad();
	}

	/*
	 * 1.初始化用户界面
	 */
	public void initUI() {
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
		this.setTitle("成绩查询");
		// 设置窗体不允许最大化，即不允许改变窗体大小
		this.setResizable(false);
		// 实例化容器对象
		container = new JPanel();
		// 把容器的布局设置为null，代表绝对布局
		container.setLayout(null);
		// 将容器对象添加到当前窗体
		this.add(container);

		// 管理界面标题
		lblTitle = new JLabel();
		lblTitle.setText("考	试 成 绩 信 息 列 表");
		lblTitle.setBounds(285, 5, 300, 80);
		lblTitle.setFont(new Font("宋体", Font.BOLD, 25));
		container.add(lblTitle);


		// 【Swing表格步骤4】：initUI()中初始化表格的方法
		initTableUI();

	}

	/**
	 * 2.绑定当前窗体的各个事件
	 */
	private void bindEvent() {
		// 自定义窗体关闭事件
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// 关闭当前窗体
		// 为当前窗体添加监听器：目的是重写windowsClosing事件的处理方法
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				window_closing(e);
			}

		});

	

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
		// TODO Auto-generated method stub
		// 初始化表格对象
		tblObj = new JTable();
		// 初始化面板的对象，并放入表格对象
		pnlTablePane = new JScrollPane(tblObj);

		// 设置表格面板的位置
		pnlTablePane.setBounds(50, 100, 700, 300);

		// 整个窗体的容器中添加表格面板
		container.add(pnlTablePane);

		// 调用方法在表格对象中显示List集合的方法
		showListData(loginName);
	}

	/**
	 * 【Swing表格步骤5】：调用方法在表格对象中显示List集合的数据
	 */

	public void showListData(String loginName) {
		java.util.List<ExamBean> list = null;



		list = examService.listByName(loginName);

		ATableModel<ExamBean> tableModel = null;

		tableModel = new ATableModel<ExamBean>(list, 6) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Object getPropValue(ExamBean bean, int columnIndex) {
				// TODO Auto-generated method stub
				if (columnIndex == 0) {
					return bean.getExamID();
				} else if (columnIndex == 1) {
					return bean.getStudentID();
				} else if (columnIndex == 2) {
					return bean.getName();
				} else if (columnIndex == 3) {
					return bean.getPaperID();
				} else if (columnIndex == 4) {
					return bean.getPaperName();
				} else if (columnIndex == 5) {
					return bean.getTotalScore();
				}
				return null;

			}

			@Override
			public String getTitle(int columnIndex) {
				// TODO Auto-generated method stub
				if (columnIndex == 0) {
					return "考试Id";
				} else if (columnIndex == 1) {
					return "学生Id";
				} else if (columnIndex == 2) {
					return "学生名称";
				} else if (columnIndex == 3) {
					return "试卷Id";
				} else if (columnIndex == 4) {
					return "试卷名称";
				} else if (columnIndex == 5) {
					return "总分";
				}
				return null;
			}

		};
		tblObj.setModel(tableModel);

	}

	protected void window_closing(WindowEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
	}

	/**
	 * 3.自定义加载类
	 */
	public void customLoad() {
		this.setTitle("成绩查询");
	}

}