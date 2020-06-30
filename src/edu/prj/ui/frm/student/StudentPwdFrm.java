package edu.prj.ui.frm.student;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import com.liuvei.common.SysFun;

import edu.prj.bean.StudentBean;
import edu.prj.service.*;
import edu.prj.service.impl.*;
import edu.prj.ui.frm.LoginFrm;
import edu.prj.ui.frm.StudentMainFrm;

public class StudentPwdFrm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6151111797465774055L;

	public String loginName = null;
	public LoginFrm loginFrm = null;
	public StudentMainFrm studentMainFrm = null;

	public StudentPwdFrm() {
		initUI();
		bindEvent();
		customLoad();
	}

	private JPanel container;
	private JLabel lblLoginPed;
	private JPasswordField txtLoginPwd;
	private JLabel lblPwd1;
	private JPasswordField txtPwd1;
	private JLabel lblPwd2;
	private JPasswordField txtPwd2;
	private JLabel lblMsg;
	private JButton btnSure;
	private JButton btnReset;

	public void initUI() {
		// 点击右上角关闭按钮时退出当前窗口
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// 设置当前窗体的宽高
		int width = 450, height = 300;
		this.setSize(width, height);
		// 设置当前窗体的标题
		this.setTitle("修改密码");
		// 设置窗体不允许最大化
		this.setResizable(false);

		// 计算当前窗体居中时的x,y坐标
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		// 定义当前窗体的位置
		this.setLocation(x, y);

		container = new JPanel();
		// 布局设为null，代表绝对布局
		container.setLayout(null);
		this.add(container);

		lblLoginPed = new JLabel();
		lblLoginPed.setBounds(100, 30, 120, 50);
		lblLoginPed.setText("原密码：");
		container.add(lblLoginPed);

		txtLoginPwd = new JPasswordField();
		txtLoginPwd.setBounds(200, 40, 170, 30);
		container.add(txtLoginPwd);

		lblPwd1 = new JLabel();
		lblPwd1.setBounds(100, 70, 120, 50);
		lblPwd1.setText("新密码：");
		container.add(lblPwd1);

		txtPwd1 = new JPasswordField();
		txtPwd1.setBounds(200, 80, 170, 30);
		container.add(txtPwd1);

		lblPwd2 = new JLabel();
		lblPwd2.setBounds(100, 110, 120, 50);
		lblPwd2.setText("确认密码：");
		container.add(lblPwd2);

		txtPwd2 = new JPasswordField();
		txtPwd2.setBounds(200, 120, 170, 30);
		container.add(txtPwd2);

		btnSure = new JButton();
		btnSure.setBounds(150, 180, 70, 30);
		btnSure.setText("确定");
		container.add(btnSure);

		btnReset = new JButton();
		btnReset.setBounds(290, 180, 70, 30);
		btnReset.setText("重置");
		container.add(btnReset);

		lblMsg = new JLabel();
		lblMsg.setBounds(110, 220, 250, 40);
		lblMsg.setForeground(Color.red);
		container.add(lblMsg);
	}

	/**
	 * 绑定事件
	 */
	public void bindEvent() {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				window_closing(e);
			}
		});
		customBindEvent();

	}

	public void customBindEvent() {
		btnSure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnSure_click();
			}

		});
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnReset_click(e);
			}

		});
		// 输入账号再输入密码后直接按回车键可触发登录事件
		txtPwd2.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("static-access")
			public void keyPressed(KeyEvent e) {
				int k = e.getKeyCode();
				if (k == e.VK_ENTER) {
					btnSure_click();
				}
			}
		});
	}

	StudentService studentService = new StudentServiceImpl();
	StudentListFrm studentListFrm = null;

	@SuppressWarnings("deprecation")
	public void btnSure_click() {
		// TODO Auto-generated method stub
		String loginPwd = txtLoginPwd.getText().trim();
		String pwd1 = txtPwd1.getText().trim();
		String pwd2 = txtPwd2.getText().trim();

		if (SysFun.isNullOrEmpty(loginPwd)) {
			lblMsg.setText("提示：原密码不能为空");
			return;
		}
		if (SysFun.isNullOrEmpty(pwd1)) {
			lblMsg.setText("提示：新密码不能为空");
			return;
		}
		if (SysFun.isNullOrEmpty(pwd2)) {
			lblMsg.setText("提示：确认密码不能为空");
			return;
		}
		if (!(pwd1.equals(pwd2))) {
			lblMsg.setText("提示：两次密码不一致！");
			return;
		}

		StudentBean item = studentService.loadByName(loginName);
		if (item == null) {
			return;
		}
		boolean isOK = item.getLoginPwd().equals(loginPwd);
		if (!isOK) {
			lblMsg.setText("提示:原密码输入有误!");
			return;
		}
		item.setLoginPwd(pwd1);
		Long result = 0L;
		@SuppressWarnings("unused")
		String errMsg = null;
		try {
			result = studentService.update(item);
		} catch (Exception ex) {
			errMsg = ex.getMessage();
		}
		// 9)处理结果修改结果
		if (result > 0) {
			JOptionPane.showMessageDialog(null, "操作成功!");
			// 成功时,重置并显示父窗体,之后,关闭当前操作
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "操作失败!");
		}
	}

	protected void btnReset_click(ActionEvent e) {
		// TODO Auto-generated method stub
		txtLoginPwd.setText("");
		txtPwd1.setText("");
		txtPwd2.setText("");
	}

	protected void window_closing(WindowEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
		if (studentMainFrm != null) {
			studentMainFrm.setVisible(true);
		}
	}

	/**
	 * 自定义加载
	 */
	public void customLoad() {
		this.setTitle("修改密码");
	}
}
