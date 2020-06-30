package edu.prj.ui.frm.grade;

import com.liuvei.common.SysFun;
import edu.prj.bean.GradeBean;
import edu.prj.service.GradeService;
import edu.prj.service.impl.GradeServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.util.Date;

public class GradeInsertFrm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5970430909715624842L;
	// 为窗体添加Panel类型的容器对象container属性，容器对象才可以放置按钮，输入框等。
	// 在initUI（）里实例化后，还要设置容器的布局
	private JPanel container;
	public GradeListFrm listFrm = null;

	private JLabel lblGradeName;// 年级名称的标签
	private JTextField txtGradeName;// 年级名称的文本框

	private JButton btnSubmit;// 提交按钮
	private JButton btnReset;// 重置按钮

	private JLabel lblMsg; // 提示信息的标签

	/**
	 * 无参构造方法
	 */
	public GradeInsertFrm() {
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
		int width = 400;
		int height = 250;
		// 设置当前窗体的宽高
		this.setSize(width, height);
		// 计算当前窗体居中时的x,y坐标
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		// 定义当前窗体的位置
		this.setLocation(x, y);
		// 设置标题
		this.setTitle("系统登录");
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
		if (listFrm != null) {
			listFrm.setVisible(true);
		}
		this.dispose();
	}

	private void customInitUI() {

		lblGradeName = new JLabel();
		lblGradeName.setText("年级名称:");
		lblGradeName.setBounds(80, 60, 80, 30);
		container.add(lblGradeName);

		txtGradeName = new JTextField();
		txtGradeName.getText();
		txtGradeName.setBounds(150, 60, 160, 30);
		container.add(txtGradeName);

		btnSubmit = new JButton();
		btnSubmit.setText("提交");
		btnSubmit.setBounds(130, 120, 70, 30);
		container.add(btnSubmit);

		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(230, 120, 70, 30);
		container.add(btnReset);

		lblMsg = new JLabel();
		lblMsg.setBounds(90, 160, 180, 30);
		lblMsg.setForeground(Color.RED);
		container.add(lblMsg);
	}

	private void custBindEvent() {
		// 提交按钮事件处理
		btnSubmit.addActionListener(e -> {
			btnSubmit_click(e);
		});
		// 重置按钮事件处理
		btnReset.addActionListener(e -> {
			btnReset_click(e);
		});
	}

	GradeService gradeService = new GradeServiceImpl();

	// 提交功能
	private void btnSubmit_click(ActionEvent e) {
		lblMsg.setText("");// 每次提交,先清空上次的提示信息
		// 1）获取输入数据
		String gradeName = txtGradeName.getText().trim();// trim忽略前后空格
		// 2)为空性判断
		if (SysFun.isNullOrEmpty(gradeName)) {
			lblMsg.setText("提示：年级名称不能为空！");
			return;
		}

		// 8)真正的添加
		GradeBean bean = new GradeBean();
		bean.setGradeName(gradeName);
		bean.setCreateOn(now());
		bean.setUpdateOn(now());

		Long result = 0L;
		@SuppressWarnings("unused")
		String errMsg = null;
		try {
			result = gradeService.insert(bean);
		} catch (Exception ex) {
			errMsg = ex.getMessage();
		}
		// 9)处理结果
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

	private Date now() {
		// TODO Auto-generated method stub
		// 获取当前时间
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		return timeStamp;
	}

	// 重置功能
	private void btnReset_click(ActionEvent e) {
		txtGradeName.setText("");
		lblMsg.setText("");
	}

	/*
	 * 3.自定义加载:在初始化UI和绑定事件之后执行
	 */
	public void customLoad() {
		this.setTitle("添加年级");
	}

}
