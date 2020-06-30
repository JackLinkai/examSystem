package edu.prj.ui.frm.exam;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;

import edu.prj.bean.ExamBean;
import edu.prj.bean.ExamItemBean;
import edu.prj.service.ExamItemService;
import edu.prj.service.ExamService;
import edu.prj.service.impl.ExamItemServiceImpl;
import edu.prj.service.impl.ExamServiceImpl;

public class ExamItemListFrm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5174390612311867154L;
	ExamListFrm listFrm = null;
	private Long id;
	private Integer num = 0;
	List<ExamBean> list = null;
	Map<Long, ExamItemBean> answer = new HashMap<>();
	ExamItemBean examitem;
	StringBuffer option;
	StringBuffer card;
	Map<Integer, String> map = new HashMap<Integer, String>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ExamItemListFrm(Long index) {

		this.id = index;
		// 1.初始化用户界面
		initUI();
		// 2.绑定事件
		bindEvent();
		// 3.自定义加载
		customLoad();

	}

	private JPanel container;// 容器对象
	private JTextArea txtQuestion;// 题目所在区域
	private JTextArea txtAnswer;// 答案所在区域
	@SuppressWarnings("unused")
	private JTextArea txtTimer;// 剩余时间
	private JRadioButton btnItemA;// 单选按钮
	private JRadioButton btnItemB;
	private JRadioButton btnItemC;
	private JRadioButton btnItemD;
	private JRadioButton btnItemE;
	private JRadioButton btnItemF;
	private JCheckBox jcbItemA;//复选框
	private JCheckBox jcbItemB;
	private JCheckBox jcbItemC;
	private JCheckBox jcbItemD;
	private JCheckBox jcbItemE;
	private JCheckBox jcbItemF;
	private ButtonGroup button;
	private JButton btnNext;// 下一题
	private JButton btnLast;// 上一题
	private JButton btnSubmit;// 提交试卷
	private JLabel lblMsg;// 提示剩余时间


	// 界面初始化
	private void initUI() {
		// 点击右上角关闭按钮时退出当前窗口
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// 设置当前窗体的宽高
		int width = 800, height = 700;
		this.setSize(width, height);
		// 设置当前窗体的标题
		this.setTitle("开始考试");
		// 设置窗体不允许最大化
		this.setResizable(false);

		// 居中
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		this.setLocation(w, h);

		container = new JPanel();
		// 布局设为null，代表绝对布局
		container.setLayout(null);

		this.add(container);

		txtQuestion = new JTextArea();
		txtAnswer = new JTextArea();
		txtTimer = new JTextArea();
		
		btnItemA = new JRadioButton();
		btnItemB = new JRadioButton();
		btnItemC = new JRadioButton();
		btnItemD = new JRadioButton();
		btnItemE = new JRadioButton();
		btnItemF = new JRadioButton();
		jcbItemA = new JCheckBox();
		jcbItemB = new JCheckBox();
		jcbItemC = new JCheckBox();
		jcbItemD = new JCheckBox();
		jcbItemE = new JCheckBox();
		jcbItemF = new JCheckBox();
		lblMsg = new JLabel();

		btnLast = new JButton();
		btnLast.setText("上一题");
		btnLast.setBounds(150, 560, 120, 40);
		container.add(btnLast);

		btnNext = new JButton();
		btnNext.setText("下一题");
		btnNext.setBounds(530, 560, 120, 40);
		container.add(btnNext);

		btnSubmit = new JButton();
		btnSubmit.setText("提交试卷");
		btnSubmit.setBounds(340, 600, 120, 40);
		container.add(btnSubmit);

		lblMsg.setForeground(java.awt.Color.RED);
		lblMsg.setBounds(550, 20, 280, 30);
		container.add(lblMsg);

		questionUI();
	}

	public void questionUI() {
		ExamService examService = new ExamServiceImpl();
		list = examService.listByID(id);
		examitem = new ExamItemBean();
		option = new StringBuffer();

		txtQuestion.setBounds(10, 140, 780, 100);
		txtQuestion.setText("  " + (num + 1) + ". " + list.get(num).getQuestion());
		txtQuestion.setEditable(false);
		txtQuestion.setBorder(BorderFactory.createTitledBorder("题目"));
		txtQuestion.setLineWrap(true);
		txtQuestion.setOpaque(false);
		container.add(txtQuestion);
		
		txtAnswer.setBounds(10, 70, 780, 50);
		if (!map.isEmpty()) {
			card = new StringBuffer();
			Set<Integer> key = map.keySet();
			for (Integer a : key) {
				card.append(a + map.get(a) + "\t");
			}
			String a = card.toString();
			txtAnswer.setText(a);
		}
		txtAnswer.setEditable(false);
		txtAnswer.setBorder(BorderFactory.createTitledBorder("答案"));
		txtAnswer.setLineWrap(true);
		txtAnswer.setOpaque(false);
		container.add(txtAnswer);

		

		if (list.get(num).getQtype() == 1) {
			
			btnItemA.setVisible(false);
			btnItemB.setVisible(false);
			btnItemC.setVisible(false);
			btnItemD.setVisible(false);
	
			btnItemE.setVisible(true);
			btnItemF.setVisible(true);

			jcbItemA.setVisible(false);
			jcbItemB.setVisible(false);
			jcbItemC.setVisible(false);
			jcbItemD.setVisible(false);
			jcbItemE.setVisible(false);
			jcbItemF.setVisible(false);

		
			
			btnItemE.setText(list.get(num).getItemE());
			btnItemF.setText(list.get(num).getItemF());

			btnItemE.setBounds(50, 450, 60, 30);
			btnItemF.setBounds(50, 500, 60, 30);

			button = new ButtonGroup();
			button.add(btnItemE);
			button.add(btnItemF);
			container.add(btnItemE);
			container.add(btnItemF);
			
		

		
		}

		if (list.get(num).getQtype() == 2) {
			btnItemA.setVisible(true);
			btnItemB.setVisible(true);
			btnItemC.setVisible(true);
			btnItemD.setVisible(true);
			btnItemE.setVisible(false);
			btnItemF.setVisible(false);
			
			jcbItemA.setVisible(false);
			jcbItemB.setVisible(false);
			jcbItemC.setVisible(false);
			jcbItemD.setVisible(false);


			btnItemA.setText(list.get(num).getItemA());
			btnItemB.setText(list.get(num).getItemB());
			btnItemC.setText(list.get(num).getItemC());
			btnItemD.setText(list.get(num).getItemD());

			btnItemA.setBounds(50, 250, 600, 30);
			btnItemB.setBounds(50, 300, 600, 30);
			btnItemC.setBounds(50, 350, 600, 30);
			btnItemD.setBounds(50, 400, 600, 30);


			button = new ButtonGroup();
			button.add(btnItemA);
			button.add(btnItemB);
			button.add(btnItemC);
			button.add(btnItemD);
			container.add(btnItemA);
			container.add(btnItemB);
			container.add(btnItemC);
			container.add(btnItemD);
		}
		if (list.get(num).getQtype() == 3) {
			btnItemA.setVisible(false);
			btnItemB.setVisible(false);
			btnItemC.setVisible(false);
			btnItemD.setVisible(false);
			btnItemE.setVisible(false);
			btnItemF.setVisible(false);

			jcbItemA.setVisible(true);
			jcbItemB.setVisible(true);
			jcbItemC.setVisible(true);
			jcbItemD.setVisible(true);


			jcbItemA.setSelected(false);
			jcbItemB.setSelected(false);
			jcbItemC.setSelected(false);
			jcbItemD.setSelected(false);


			jcbItemA.setText(list.get(num).getItemA());
			jcbItemB.setText(list.get(num).getItemB());
			jcbItemC.setText(list.get(num).getItemC());
			jcbItemD.setText(list.get(num).getItemD());


			jcbItemA.setBounds(50, 250, 600, 30);
			jcbItemB.setBounds(50, 300, 600, 30);
			jcbItemC.setBounds(50, 350, 600, 30);
			jcbItemD.setBounds(50, 400, 600, 30);


			container.add(jcbItemA);
			container.add(jcbItemB);
			container.add(jcbItemC);
			container.add(jcbItemD);
	
		}
		examitem.setExamID(list.get(num).getExamID());
		examitem.setQuestionID(list.get(num).getQuestionId());
		examitem.setStdAnswer(list.get(num).getAnswer());
		examitem.setStdScore(list.get(num).getScore());
		if (examitem.getStuAnswer() != null) {
			answer.put(examitem.getQuestionID(), examitem);

		}
	}

	Timer timer = null;

	// 绑定事件
	private void bindEvent() {
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				timer_click(e);
			}
		});

		// 下一题按钮
		btnNext.addActionListener(e -> {
			btnNext_click();
		});

		// 上一题按钮
		btnLast.addActionListener(e -> {
			btnLast_click();
		});

		// 提交按钮
		btnSubmit.addActionListener(e -> {
			btnSubmit_click();
		});

		// 选项按钮
		btnItemA.addActionListener(e -> {
			option.delete(0, option.length());
			option.append("A");
			examitem.setStuAnswer(option.toString());
			answer.put(examitem.getQuestionID(), examitem);

		});

		btnItemB.addActionListener(e -> {
			option.delete(0, option.length());
			option.append("B");
			examitem.setStuAnswer(option.toString());
			answer.put(examitem.getQuestionID(), examitem);
		});

		btnItemC.addActionListener(e -> {
			option.delete(0, option.length());
			option.append("C");
			examitem.setStuAnswer(option.toString());
			answer.put(examitem.getQuestionID(), examitem);
		});

		btnItemD.addActionListener(e -> {
			option.delete(0, option.length());
			option.append("D");
			examitem.setStuAnswer(option.toString());
			answer.put(examitem.getQuestionID(), examitem);
		});
		btnItemE.addActionListener(e -> {
			option.delete(0, option.length());
			option.append("E");
			examitem.setStuAnswer(option.toString());
			answer.put(examitem.getQuestionID(), examitem);
		});
		btnItemF.addActionListener(e -> {
			option.delete(0, option.length());
			option.append("F");
			examitem.setStuAnswer(option.toString());
			answer.put(examitem.getQuestionID(), examitem);
		});

	}

	// 提交按钮事件
	private void btnSubmit_click() {
		// TODO Auto-generated method stub
		isChoose();
		String title = "系统提示";
		String message = "确定要提交";
		Long num = null;
		int option = JOptionPane.YES_NO_CANCEL_OPTION;
		int buttonValue = JOptionPane.showConfirmDialog(null, message, title, option);
		if (buttonValue == JOptionPane.YES_OPTION) {

			timer.stop();

			Set<Long> key = answer.keySet();
			for (Long a : key) {
				ExamItemService examItemService = new ExamItemServiceImpl();
				examItemService.insert(answer.get(a));
			}

			ExamItemService examItemService = new ExamItemServiceImpl();
			examItemService.Score();
			num = examItemService.sum(id);
			ExamService examService = new ExamServiceImpl();
			ExamBean exam = examService.load((long) id);
			exam.setIsMark(1l);
			exam.setTotalScore((double) num);
			examService.update(exam);
			this.dispose();
			if (listFrm != null) {
				listFrm.btnUpdate_click(null);
				listFrm.setVisible(true);
			}

		}

	}

	// 上一题按钮事件
	private void btnLast_click() {
		// TODO Auto-generated method stub
		if (list.get(num).getQtype() == 1) {
			button.clearSelection();
		}if (list.get(num).getQtype() == 2) {
			button.clearSelection();
		}
		if (list.get(num).getQtype() == 3) {
			isChoose();
		}
		if (!option.toString().isEmpty()) {
			map.put((num + 1), option.toString());
		}
		num--;
		if (num >= 0) {
			questionUI();
		} else {
			num = 0;
			JOptionPane.showMessageDialog(null, "已经是第一题");
			questionUI();
		}
	}

	// 下一题按钮事件
	private void btnNext_click() {
		if (list.get(num).getQtype() == 1) {
			button.clearSelection();
		}
		if (list.get(num).getQtype() == 2) {
			button.clearSelection();
		}
		if (list.get(num).getQtype() == 3) {
			isChoose();
		}
		if (!option.toString().isEmpty()) {
			map.put((num + 1), option.toString());
		}
		num++;
		if (num < list.size()) {
			questionUI();

		} else {
			num = list.size() - 1;
			JOptionPane.showMessageDialog(null, "已经是最后一题");
			questionUI();
		}

	}

	Long total = 1200L;

	// 时间结束自动提交事件
	private void timer_click(ActionEvent e) {
		// TODO Auto-generated method stub

		Long hh = total / 3600;
		Long dd = total % 3600 / 60;
		Long ss = total % 3600 % 60;
		lblMsg.setText("距离考试结束还有：" + toStr(hh) + ":" + toStr(dd) + ":" + toStr(ss));
		if (total == 0) {
			timer.stop();
			JOptionPane.showMessageDialog(null, "考试时间结束，请提交试卷");

		}
		total--;
	}

	public String toStr(Long num) {
		String str = num.toString();
		if (str.length() == 1) {
			str = "0" + str;
		}
		return str;
	}

	private void isChoose() {
		if (jcbItemA.isSelected()) {
			option.append("A");
		}
		if (jcbItemB.isSelected()) {
			option.append("B");
		}
		if (jcbItemC.isSelected()) {
			option.append("C");
		}
		if (jcbItemD.isSelected()) {
			option.append("D");
		}
		if (!option.toString().isEmpty()) {
			examitem.setStuAnswer(option.toString());
			answer.put(examitem.getQuestionID(), examitem);
		}
	}

	private void customLoad() {

	}

}
