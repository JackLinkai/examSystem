package edu.prj.ui.frm.paper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.*;

import javax.swing.*;

import com.liuvei.common.SysFun;
import com.liuvei.common.win.IdText;
import com.liuvei.common.win.IdTextModel;

import edu.prj.bean.*;
import edu.prj.service.*;
import edu.prj.service.impl.*;
import edu.prj.util.*;

public class PaperInsertFrm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4964230072090923758L;
	// 为窗体添加Panel类型的容器对象container属性，容器对象才可以放置按钮，输入框等。
	// 在initUI（）里实例化后，还要设置容器的布局
	private JPanel container;

	private JLabel lblPaperName;// 试卷名称标签
	private JTextField txtPaperName;// 试卷名称文本框
	private JLabel lblTotalScore;// 总分数标签
	private JTextField txtTotalScore;// 总分数文本框
	private JLabel lblPerScore;// 每题分数标签
	private JTextField txtPerScore;// 每题分数文本框
	private JLabel lblQuestionNum;// 题目数量标签
	private JTextField txtQuestionNum;// 题目数量文本框
	private JLabel lblExamMinute;// 考试时间标签
	private JTextField txtExamMinute;// 考试时间文本框
	private JLabel lblStartOn;// 开始时间
	private DateButton dbStartOn;
	private JLabel lblEndOn;// 结束时间
	private DateButton dbEndOn;
	private JLabel lblHasGenerate;// 是否生成
	private JComboBox<IdText> cobHasGenerate;
	private JButton btnSubmit;// 提交按钮
	private JButton btnReset;// 重置按钮
	private JLabel lblMsg;// 提示信息的标签

	PaperService paperService = new PaperServiceImpl();
	PaperListFrm listFrm = null;
	SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 无参构造方法
	 */
	public PaperInsertFrm() {
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
	private void initUI() {
		// 定义当前窗体的宽高
		int width = 400;
		int height = 550;
		// 设置当前窗体的宽高
		this.setSize(width, height);
		// 计算当前窗体居中时的x,y坐标
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		// 定义当前窗体的位置
		this.setLocation(x, y);
		// 设置标题
		this.setTitle("添加试卷");
		// 设置窗体不允许最大化，即不允许改变窗体大小
		this.setResizable(false);
		// 实例化容器对象
		container = new JPanel();
		// 把容器的布局设置为null，代表绝对布局
		container.setLayout(null);
		// 将容器对象添加到当前窗体
		this.add(container);

		lblPaperName = new JLabel();
		lblPaperName.setText("试卷名称：");
		lblPaperName.setBounds(50, 50, 80, 30);
		container.add(lblPaperName);

		txtPaperName = new JTextField();
		txtPaperName.setBounds(130, 50, 200, 30);
		container.add(txtPaperName);

		lblTotalScore = new JLabel();
		lblTotalScore.setText("总分：");
		lblTotalScore.setBounds(60, 100, 80, 30);
		container.add(lblTotalScore);

		txtTotalScore = new JTextField();
		txtTotalScore.setBounds(130, 100, 200, 30);
		container.add(txtTotalScore);

		lblPerScore = new JLabel();
		lblPerScore.setText("每题分数：");
		lblPerScore.setBounds(50, 150, 80, 30);
		container.add(lblPerScore);

		txtPerScore = new JTextField();
		txtPerScore.setBounds(130, 150, 200, 30);
		container.add(txtPerScore);

		lblQuestionNum = new JLabel();
		lblQuestionNum.setText("题目数：");
		lblQuestionNum.setBounds(60, 200, 80, 30);
		container.add(lblQuestionNum);

		txtQuestionNum = new JTextField();
		txtQuestionNum.setBounds(130, 200, 200, 30);
		container.add(txtQuestionNum);

		lblExamMinute = new JLabel();
		lblExamMinute.setText("考试分钟：");
		lblExamMinute.setBounds(50, 250, 80, 30);
		container.add(lblExamMinute);

		txtExamMinute = new JTextField();
		txtExamMinute.setBounds(130, 250, 200, 30);
		container.add(txtExamMinute);

		lblStartOn = new JLabel();
		lblStartOn.setText("有效开始日期：");
		lblStartOn.setBounds(40, 300, 100, 30);
		container.add(lblStartOn);

		dbStartOn = new DateButton();
		dbStartOn.setBounds(130, 300, 200, 30);
		container.add(dbStartOn);

		lblEndOn = new JLabel();
		lblEndOn.setText("有效结束日期： ");
		lblEndOn.setBounds(40, 350, 100, 30);
		container.add(lblEndOn);

		dbEndOn = new DateButton();
		dbEndOn.setBounds(130, 350, 200, 30);
		container.add(dbEndOn);

		lblHasGenerate = new JLabel();
		lblHasGenerate.setText("是否已生成：");
		lblHasGenerate.setBounds(50, 400, 80, 30);
		container.add(lblHasGenerate);

		cobHasGenerate = new JComboBox<IdText>();
		cobHasGenerate.setBounds(130, 400, 200, 30);
		container.add(cobHasGenerate);

		btnSubmit = new JButton();
		btnSubmit.setText("提交");
		btnSubmit.setBounds(100, 450, 80, 30);
		container.add(btnSubmit);

		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(250, 450, 80, 30);
		container.add(btnReset);

		lblMsg = new JLabel();
		lblMsg.setFont(new Font("宋体", Font.BOLD, 15));
		lblMsg.setForeground(Color.RED);
		lblMsg.setBounds(60, 490, 350, 30);
		container.add(lblMsg);

	}

	/**
	 * 2.绑定当前窗体的各个事件
	 */
	private void bindEvent() {
		// 设置默认的关闭操作，点击右上角的关闭按钮时退出整个应用程序
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// 为当前窗体添加监听器：目的是重写windowsClosing事件的处理方法
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// 这里写定制操作
				window_closing(e);
			}
		});

		customBindEvent();
	}

	protected void window_closing(WindowEvent e) {
		// TODO Auto-generated method stub
		// 关闭主窗体，显示登陆窗体
		if (listFrm != null) {
			listFrm.setVisible(true);
		}

		this.dispose();
	}

	private void customBindEvent() {
		// 提交重置按钮的事件处理
		btnSubmit.addActionListener(e -> {
			btnSubmit_click(e);
		});

		// 重置按钮事件处理
		btnReset.addActionListener(e -> {
			btnReset_click(e);
		});

	}

	//重置功能
	private void btnReset_click(ActionEvent e) {
		// TODO Auto-generated method stub
		txtPaperName.setText("");
		txtTotalScore.setText("");
		txtPerScore.setText("");
		txtQuestionNum.setText("");
		txtExamMinute.setText("");
		bindComboBoxData();
	}

	private void bindComboBoxData() {
		// TODO Auto-generated method stub
		java.util.List<IdText> idTestList = new java.util.ArrayList<IdText>();
		idTestList.add(new IdText(Long.valueOf(0L), "否"));
		idTestList.add(new IdText(Long.valueOf(1L), "是"));
		IdTextModel model = new IdTextModel(idTestList);
		cobHasGenerate.setModel(model);
	}

	// 提交功能
	private void btnSubmit_click(ActionEvent e) {
		// TODO Auto-generated method stub
		lblMsg.setText("");// 每次提交,先清空上次的提示信息
		// 1）获取输入数据
		String paperName = txtPaperName.getText().trim();
		String totalScore = txtTotalScore.getText().trim();
		String perScore = txtPerScore.getText().trim();
		String questionNum = txtQuestionNum.getText().trim();
		String examMinute = txtExamMinute.getText().trim();
		IdText itemHasGenerate = (IdText) cobHasGenerate.getSelectedItem();
		Long hasGenerate = itemHasGenerate.getId();

		// 为空性判断
		if (SysFun.isNullOrEmpty(paperName)) {
			lblMsg.setText("试卷名称不能为空。");
			return;
		}

		if (SysFun.isNullOrEmpty(totalScore)) {
			lblMsg.setText("总分不能为空。");
			return;
		}

		if (SysFun.isNullOrEmpty(perScore)) {
			lblMsg.setText("每题的分数不能为空。");
			return;
		}

		if (SysFun.isNullOrEmpty(questionNum)) {
			lblMsg.setText("题目数量不能为空。");
			return;
		}

		if (SysFun.isNullOrEmpty(examMinute)) {
			lblMsg.setText("考试时间不能为空。");
			return;
		}

		// 业务合法性判断：如名称不能重复
		PaperBean item = paperService.loadByName(paperName);
		if (item != null) {
			lblMsg.setText("该考卷已存在");
			return;
		}

		// 真正的添加
		PaperBean bean = new PaperBean();
		bean.setPaperName(paperName);
		bean.setTotalScore(Double.parseDouble(totalScore));
		bean.setPerScore(Double.parseDouble(perScore));
		bean.setQuestionNum(Long.parseLong(questionNum));
		bean.setExamMinute(Long.parseLong(examMinute));
		bean.setHasGenerate(hasGenerate);
		try {
			bean.setStartOn(time.parse(dbStartOn.getText()));
			bean.setEndOn(time.parse(dbEndOn.getText()));
		} catch (ParseException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
		// 9)处理结果
		Long result = Long.valueOf(0L);
		@SuppressWarnings("unused")
		String errMsg = null;
		try {
			result = paperService.insert(bean);
		} catch (Exception ex) {
			errMsg = ex.getMessage();
		}

		if (result > 0) {
			// 成功时弹出一个对话框，显示操作成功
			JOptionPane.showMessageDialog(null, "操作成功！");
			// 成功时重置并显示父窗体，关闭当前窗体
			if (listFrm != null) {
				listFrm.btnReset_click(null);
				listFrm.setVisible(true);
			}
			this.dispose();

		} else {
			JOptionPane.showMessageDialog(null, "操作失败！");
		}

	}

	/**
	 * 3.自定义加载类
	 */
	public void customLoad() {
		this.setTitle("添加试卷");
		bindComboBoxData();
	}

}
