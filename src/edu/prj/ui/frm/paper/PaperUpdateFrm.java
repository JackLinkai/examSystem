package edu.prj.ui.frm.paper;

import com.liuvei.common.SysFun;
import com.liuvei.common.win.IdText;
import com.liuvei.common.win.IdTextModel;

import edu.prj.bean.PaperBean;
import edu.prj.service.PaperService;
import edu.prj.service.impl.PaperServiceImpl;
import edu.prj.util.DateButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PaperUpdateFrm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7427560874181114588L;
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
	private JButton btnSubmit;// 提交按钮；
	private JButton btnReset;// 重置按钮

	private JLabel lblMsg; // 提示信息的标签

	public PaperListFrm listFrm = null;
	SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	PaperService paperService = new PaperServiceImpl();

	/**
	 * 无参构造方法
	 */
	public PaperUpdateFrm() {
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
		int width = 500;
		int height = 700;
		// 设置当前窗体的宽高
		this.setSize(width, height);
		// 计算当前窗体居中时的x,y坐标
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		// 定义当前窗体的位置
		this.setLocation(x, y);
		// 设置标题
		this.setTitle("修改试卷");
		// 设置窗体不允许最大化，即不允许改变窗体大小
		this.setResizable(false);
		// 实例化容器对象
		container = new JPanel();
		// 把容器的布局设置为null，代表绝对布局
		container.setLayout(null);
		// 将容器对象添加到当前窗体
		this.add(container);
		customInitUI();
	}

	/*
	 * 2.绑定事件
	 */
	public void bindEvent() {
		// 设置默认的关闭操作，点击右上角的关闭按钮时退出整个应用程序
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// 这里写定制操作
				window_closing(e);
			}
		});
		custBindEvent();
	}

	public void window_closing(WindowEvent e) {
		// 关闭主窗体，显示登陆窗体
		if (this.listFrm != null) {
			listFrm.setVisible(true);
		}
		this.dispose();
	}

	private void customInitUI() {

		lblPaperName = new JLabel();
		lblPaperName.setText("试卷名称");
		lblPaperName.setBounds(60, 50, 80, 40);
		container.add(lblPaperName);

		txtPaperName = new JTextField();
		txtPaperName.setBounds(140, 50, 230, 40);
		container.add(txtPaperName);

		lblTotalScore = new JLabel();
		lblTotalScore.setText("总分");
		lblTotalScore.setBounds(60, 110, 80, 40);
		container.add(lblTotalScore);

		txtTotalScore = new JTextField();
		txtTotalScore.setBounds(140, 110, 230, 40);
		container.add(txtTotalScore);

		lblPerScore = new JLabel();
		lblPerScore.setText("每题分数");
		lblPerScore.setBounds(60, 170, 80, 40);
		container.add(lblPerScore);

		txtPerScore = new JTextField();
		txtPerScore.setBounds(140, 170, 230, 40);
		container.add(txtPerScore);

		lblQuestionNum = new JLabel();
		lblQuestionNum.setText("题目数");
		lblQuestionNum.setBounds(60, 230, 80, 40);
		container.add(lblQuestionNum);

		txtQuestionNum = new JTextField();
		txtQuestionNum.setBounds(140, 230, 230, 40);
		container.add(txtQuestionNum);

		lblExamMinute = new JLabel();
		lblExamMinute.setText("考试分钟");
		lblExamMinute.setBounds(60, 290, 80, 40);
		container.add(lblExamMinute);

		txtExamMinute = new JTextField();
		txtExamMinute.setBounds(140, 290, 230, 40);
		container.add(txtExamMinute);

		lblStartOn = new JLabel();
		lblStartOn.setText("有效开始日期");
		lblStartOn.setBounds(40, 350, 100, 40);
		container.add(lblStartOn);

		dbStartOn = new DateButton();
		dbStartOn.setBounds(140, 350, 230, 40);
		container.add(dbStartOn);

		lblEndOn = new JLabel();
		lblEndOn.setText("有效结束日期 ");
		lblEndOn.setBounds(40, 410, 100, 40);
		container.add(lblEndOn);

		dbEndOn = new DateButton();
		dbEndOn.setBounds(140, 410, 230, 40);
		container.add(dbEndOn);

		lblHasGenerate = new JLabel();
		lblHasGenerate.setText("是否已生成");
		lblHasGenerate.setBounds(60, 470, 80, 40);
		container.add(lblHasGenerate);

		cobHasGenerate = new JComboBox<IdText>();
		cobHasGenerate.setBounds(140, 470, 230, 40);
		container.add(cobHasGenerate);

		btnSubmit = new JButton();
		btnSubmit.setText("提交");
		btnSubmit.setBounds(130, 540, 80, 40);
		container.add(btnSubmit);

		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(300, 540, 80, 40);
		container.add(btnReset);

		lblMsg = new JLabel();
		lblMsg.setForeground(Color.RED);
		lblMsg.setBounds(60, 600, 350, 30);
		container.add(lblMsg);
	}

	private void custBindEvent() {
		// 提交按钮事件处理
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnSubmit_click(e);
			}

		});
		// 重置按钮事件处理
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnReset_click(e);
			}

		});
	}

	// 提交功能
	private void btnSubmit_click(ActionEvent e) {
		// 1）获取输入数据
		// trim忽略前后空格
		IdText itemHasGenerate = (IdText) cobHasGenerate.getSelectedItem();
		String paperName = txtPaperName.getText().trim();
		String totalScore = txtTotalScore.getText().trim();	
		String perScore = txtPerScore.getText().trim();
		String questionNum = txtQuestionNum.getText().trim();
		String examMinute = txtExamMinute.getText().trim();
		Long hasGenerate = itemHasGenerate.getId();

		// 2)为空性判断
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
		if (cobHasGenerate.getSelectedIndex() == -1) {
			lblMsg.setText("提示：请选择是否生成");
			return;
		}
		// 业务合法性判断：如名称不能重复
		PaperBean item = paperService.loadByName(paperName);
		if (item != null && item.getPaperID() != pk) {
			lblMsg.setText("该考卷已存在");
			return;
		}

		// 修改时,根据名称取得对象
		// a)对象的主键如果与当前修改的主键一致,说明取得的是当前修改对象,允许修改
		// b)对象的主键如果与当前修改的主键不一致,说明存在另一个对象的名称跟当前修改内容一致,说明名称重复
		// 5)真正的修改
		PaperBean bean = new PaperBean();
		bean.setPaperID(pk);
		bean.setPaperName(paperName);
		bean.setTotalScore(Double.valueOf(totalScore));
		bean.setPerScore(Double.valueOf(perScore));
		bean.setQuestionNum(Long.valueOf(questionNum));
		bean.setExamMinute(Long.valueOf(examMinute));
		bean.setHasGenerate(hasGenerate);
		try {
			bean.setStartOn(time.parse(dbStartOn.getText()));
			bean.setEndOn(time.parse(dbEndOn.getText()));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 6)处理结果
		Long result = Long.valueOf(0L);
		@SuppressWarnings("unused")
		String errMsg = null;

		try {
			result = paperService.update(bean);
		} catch (Exception ex) {
			errMsg = ex.getMessage();
		}
		// 9)处理结果修改结果
		if (result > 0) {
			JOptionPane.showMessageDialog(null, "操作成功!");
			// 成功时,重置并显示父窗体,之后,关闭当前操作
			if (listFrm != null) {
				listFrm.btnReset_click(null);
				listFrm.setVisible(true);
			}
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "操作失败!");
		}
	}

	// 重置功能
	private void btnReset_click(ActionEvent e) {
		bindComboBoxData();
		loadData();
	}

	private void bindComboBoxData() {
		// TODO Auto-generated method stub
		java.util.List<IdText> idTestList = new java.util.ArrayList<IdText>();
		idTestList.add(new IdText(Long.valueOf(0L), "否"));
		idTestList.add(new IdText(Long.valueOf(1L), "是"));
		IdTextModel model = new IdTextModel(idTestList);
		cobHasGenerate.setModel(model);
	}

	/*
	 * 3.自定义加载:在初始化UI和绑定事件之后执行
	 */
	public void customLoad() {
		bindComboBoxData();
		this.setTitle("修改试卷信息");

	}

	/**
	 * 【修改窗体之加载数据--步骤1】声明当前修改的数据的主键值。从列表数据中传递过来
	 */
	Long pk = null;

	/**
	 * 【修改窗体之加载数据--步骤2】根据pk加载要修改的主键对应的数据，并显示窗体的标题
	 */
	public void loadData() {
		if (!this.getTitle().contains("主键")) {
			this.setTitle(this.getTitle() + "-->正在修改id为【" + pk + "】的试卷信息");
		}

		PaperBean bean = paperService.load(pk);
		if (bean != null) {
			txtPaperName.setText(bean.getPaperName());
			txtTotalScore.setText(bean.getTotalScore().toString());
			txtPerScore.setText(bean.getPerScore().toString());
			txtQuestionNum.setText(bean.getQuestionNum().toString());
			txtExamMinute.setText(bean.getExamMinute().toString());
			cobHasGenerate.getModel().setSelectedItem(new IdText(bean.getHasGenerate(), ""));
		}
	}
}